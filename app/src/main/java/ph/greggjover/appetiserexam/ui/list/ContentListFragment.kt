package ph.greggjover.appetiserexam.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ph.greggjover.appetiserexam.R
import ph.greggjover.appetiserexam.data.database.content.GenreWithContent
import ph.greggjover.appetiserexam.databinding.FragmentContentListBinding
import ph.greggjover.appetiserexam.ui.extensions.viewBindingLifeCycle
import ph.greggjover.appetiserexam.ui.list.epoxy.contentEpoxyHolder
import ph.greggjover.appetiserexam.ui.list.epoxy.genreEpoxyHolder

/**
 * Fragment that displays all available content, which is segregated by Genre name
 */
@AndroidEntryPoint
class ContentListFragment : Fragment() {

    private var binding by viewBindingLifeCycle<FragmentContentListBinding>()
    private val viewModel by viewModels<ContentListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configure the SwipeRefreshLayout to fetch the latest content upon refresh
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.getContent() }

        // A Flow is dispatched to observe Content changes from the Database, and the updated content
        // will be used to immediately update the UI Thread
        viewModel.contentLiveData.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = false
            buildContentRecyclerView(it)
        }

        // If there is an error fetching the latest content from the backend, display an error Snackbar
        viewModel.contentFetchFailedEvent.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = false
            Snackbar.make(
                binding.root,
                getString(R.string.fetch_failed_message),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * Builds the EpoxyRecyclerView from the content returned
     *
     * @param contents - List of [GenreWithContent]
     */
    private fun buildContentRecyclerView(contents: List<GenreWithContent>) {
        val contentRecyclerView = binding.contentRecyclerView
        contentRecyclerView.withModels {
            contents.forEach { genreWithContent ->
                genreEpoxyHolder {
                    id("ID_GENRE_${genreWithContent.genre}")
                    genre(genreWithContent.genre)
                }
                genreWithContent.content.forEach { content ->
                    contentEpoxyHolder {
                        id("ID_CONTENT_${content.trackName}")
                        content(content)
                        contentClick {
                            findNavController().navigate(
                                ContentListFragmentDirections.listToDetails(
                                    it
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
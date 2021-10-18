package ph.greggjover.appetiserexam.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
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

        // A Flow is dispatched to observe Content changes from the Database, and the updated content
        // will be used to immediately update the UI Thread
        viewModel.contentLiveData.observe(viewLifecycleOwner) {
            buildContentRecyclerView(it)
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
package ph.greggjover.appetiserexam.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ph.greggjover.appetiserexam.databinding.FragmentContentListBinding
import ph.greggjover.appetiserexam.ui.extensions.viewBindingLifeCycle
import ph.greggjover.appetiserexam.ui.list.adapter.ContentListAdapter

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

        val adapter = ContentListAdapter(
            // Clicking a Content will redirect the user the Details screen
            clickAction = {
                findNavController().navigate(
                    ContentListFragmentDirections.listToDetails(
                        it
                    )
                )
            }
        )
        binding.contentRecyclerView.adapter = adapter

        // A Flow is dispatched to observe Content changes from the Database, and the updated content
        // will be immediately sent to the Adapter to update the UI
        viewModel.contentLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}
package ph.greggjover.appetiserexam.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ph.greggjover.appetiserexam.databinding.FragmentContentListBinding
import ph.greggjover.appetiserexam.ui.extensions.viewBindingLifeCycle

/**
 * Fragment that displays the list of Content
 */
@AndroidEntryPoint
class ContentListFragment : Fragment() {

    private var binding by viewBindingLifeCycle<FragmentContentListBinding>()

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

        // TODO: Add Code Here
    }
}
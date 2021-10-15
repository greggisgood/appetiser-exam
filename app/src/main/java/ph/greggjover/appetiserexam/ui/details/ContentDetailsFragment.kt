package ph.greggjover.appetiserexam.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ph.greggjover.appetiserexam.databinding.FragmentContentDetailsBinding
import ph.greggjover.appetiserexam.ui.extensions.viewBindingLifeCycle

/**
 * Fragment that displays Content information in more detail
 */
@AndroidEntryPoint
class ContentDetailsFragment : Fragment() {

    private var binding by viewBindingLifeCycle<FragmentContentDetailsBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Add implementation here
    }
}
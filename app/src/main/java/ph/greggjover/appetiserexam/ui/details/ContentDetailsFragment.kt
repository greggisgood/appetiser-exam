package ph.greggjover.appetiserexam.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ph.greggjover.appetiserexam.R
import ph.greggjover.appetiserexam.databinding.FragmentContentDetailsBinding
import ph.greggjover.appetiserexam.ui.extensions.viewBindingLifeCycle

/**
 * Fragment that displays Content information in more detail
 */
@AndroidEntryPoint
class ContentDetailsFragment : Fragment() {

    private var binding by viewBindingLifeCycle<FragmentContentDetailsBinding>()
    private val navArgs by navArgs<ContentDetailsFragmentArgs>()

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

        with(binding) {
            val content = navArgs.content
            contentImageView.load(content.artworkUrlForDetail)
            contentTitleTextView.text = content.trackName
            contentGenreTextView.text = content.primaryGenreName
            contentDescriptionTextView.text = content.longDescription
            contentPriceTextView.text = getString(R.string.price_formatter, content.trackPrice)
        }
    }
}
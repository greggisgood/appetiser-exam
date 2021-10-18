package ph.greggjover.appetiserexam.ui.list.epoxy

import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import ph.greggjover.appetiserexam.R
import ph.greggjover.appetiserexam.ui.extensions.BaseEpoxyHolder

/**
 * EpoxyHolder class that displays the Genre of a list of contents
 */
@EpoxyModelClass
abstract class GenreEpoxyHolder : EpoxyModelWithHolder<GenreEpoxyHolder.Holder>() {

    @EpoxyAttribute
    lateinit var genre: String

    override fun getDefaultLayout() = R.layout.item_genre

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.bind(genre)
    }

    class Holder : BaseEpoxyHolder() {
        private val genreTextView by bind<TextView>(R.id.genreTextView)

        fun bind(genre: String) {
            genreTextView.text = genre
        }
    }
}
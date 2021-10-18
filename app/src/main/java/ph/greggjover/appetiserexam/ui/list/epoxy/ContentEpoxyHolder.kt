package ph.greggjover.appetiserexam.ui.list.epoxy

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import ph.greggjover.appetiserexam.R
import ph.greggjover.appetiserexam.data.database.content.Content
import ph.greggjover.appetiserexam.ui.extensions.BaseEpoxyHolder

/**
 * EpoxyModel class that displays [Content] details
 */
@EpoxyModelClass
abstract class ContentEpoxyHolder : EpoxyModelWithHolder<ContentEpoxyHolder.Holder>() {

    @EpoxyAttribute
    lateinit var content: Content

    @EpoxyAttribute
    lateinit var contentClick: (Content) -> Unit

    override fun getDefaultLayout() = R.layout.item_content

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.bind(content, contentClick)
    }

    class Holder : BaseEpoxyHolder() {
        private val contentLayout by bind<ConstraintLayout>(R.id.contentLayout)
        private val contentImageView by bind<ImageView>(R.id.contentImageView)
        private val contentTextView by bind<TextView>(R.id.contentTitleTextView)
        private val contentPriceTextView by bind<TextView>(R.id.contentPriceTextView)

        fun bind(content: Content, contentClick: (Content) -> Unit) {
            contentImageView.load(content.artworkUrlForList)
            contentTextView.text = content.trackName
            contentPriceTextView.text = context.getString(R.string.price_formatter, content.trackPrice)
            contentLayout.setOnClickListener { contentClick(content) }
        }
    }
}
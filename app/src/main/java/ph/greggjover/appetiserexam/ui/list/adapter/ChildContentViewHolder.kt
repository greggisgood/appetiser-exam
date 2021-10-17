package ph.greggjover.appetiserexam.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ph.greggjover.appetiserexam.R
import ph.greggjover.appetiserexam.data.database.content.Content
import ph.greggjover.appetiserexam.databinding.ItemChildContentBinding

/**
 * ViewHolder class that displays [Content] information to [ChildContentAdapter]
 */
class ChildContentViewHolder(
    private val binding: ItemChildContentBinding,
    private val clickAction: (Content) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(content: Content) {
        with(binding) {
            contentImageView.load(content.artworkUrlForList)
            contentTitleTextView.text = content.trackName
            contentPriceTextView.text =
                contentPriceTextView.context.getString(R.string.price_formatter, content.trackPrice)
            contentLayout.setOnClickListener { clickAction(content) }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            clickAction: (Content) -> Unit,
        ) = ChildContentViewHolder(
            binding = ItemChildContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickAction = clickAction,
        )
    }
}
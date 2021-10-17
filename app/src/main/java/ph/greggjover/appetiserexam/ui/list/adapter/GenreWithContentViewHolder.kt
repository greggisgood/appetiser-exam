package ph.greggjover.appetiserexam.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.greggjover.appetiserexam.data.database.content.Content
import ph.greggjover.appetiserexam.data.database.content.GenreWithContent
import ph.greggjover.appetiserexam.databinding.ItemGenreWithContentBinding

/**
 * Main ViewHolder class that displays the Genre name, while displaying the [Content] by
 * initializing [ChildContentAdapter] and submitting the list of [Content]
 */
class GenreWithContentViewHolder(
    private val binding: ItemGenreWithContentBinding,
    private val clickAction: (Content) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(entry: GenreWithContent) {
        with(binding) {
            genreTextView.text = entry.genre

            val adapter = ChildContentAdapter(clickAction)
            genreContentRecyclerView.adapter = adapter
            adapter.submitList(entry.content)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            clickAction: (Content) -> Unit,
        ) = GenreWithContentViewHolder(
            binding = ItemGenreWithContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickAction = clickAction,
        )
    }

}
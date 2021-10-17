package ph.greggjover.appetiserexam.ui.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import ph.greggjover.appetiserexam.data.database.content.Content
import ph.greggjover.appetiserexam.data.database.content.GenreWithContent

/**
 * Main Adapter class that displays all available content.
 *
 * The Genre name is displayed in [GenreWithContentViewHolder] while the [Content] is displayed
 * through [ChildContentAdapter] inside the same ViewHolder class.
 */
class ContentListAdapter(
    private val clickAction: (Content) -> Unit,
): ListAdapter<GenreWithContent, GenreWithContentViewHolder>(CONTENT_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GenreWithContentViewHolder.create(parent, clickAction)

    override fun onBindViewHolder(holder: GenreWithContentViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val CONTENT_COMPARATOR = object : DiffUtil.ItemCallback<GenreWithContent>() {
            override fun areItemsTheSame(
                oldItem: GenreWithContent,
                newItem: GenreWithContent
            ): Boolean {
                return (oldItem.genre == newItem.genre) && (oldItem.content == newItem.content)
            }

            override fun areContentsTheSame(
                oldItem: GenreWithContent,
                newItem: GenreWithContent
            ) = oldItem == newItem
        }
    }
}
package ph.greggjover.appetiserexam.ui.list.adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ph.greggjover.appetiserexam.data.database.content.Content

/**
 * RecyclerView Adapter class that displays [Content] through [ChildContentViewHolder]
 *
 * This is a child Adapter which is initialized in [GenreWithContentViewHolder]
 */
class ChildContentAdapter(
    private val clickAction: (Content) -> Unit,
) : ListAdapter<Content, ChildContentViewHolder>(CONTENT_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ChildContentViewHolder.create(parent, clickAction)

    override fun onBindViewHolder(holder: ChildContentViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val CONTENT_COMPARATOR = object : DiffUtil.ItemCallback<Content>() {
            override fun areItemsTheSame(
                oldItem: Content,
                newItem: Content
            ) = oldItem.trackName == newItem.trackName

            override fun areContentsTheSame(
                oldItem: Content,
                newItem: Content
            ) = oldItem == newItem
        }
    }
}
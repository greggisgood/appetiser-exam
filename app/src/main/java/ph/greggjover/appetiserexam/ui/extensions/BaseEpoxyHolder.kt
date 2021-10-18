package ph.greggjover.appetiserexam.ui.extensions

import android.content.Context
import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A pattern for easier View Binding with an [EpoxyHolder]
 * Taken from Epoxy
 *
 * https://github.com/airbnb/epoxy/wiki/Kotlin-Model-Examples#with-view-holders
 */
abstract class BaseEpoxyHolder : EpoxyHolder() {
    private lateinit var view: View
    val context: Context
        get() = view.context

    override fun bindView(itemView: View) {
        view = itemView
    }

    protected fun <V : View> bind(id: Int): ReadOnlyProperty<BaseEpoxyHolder, V> =
        Lazy { holder : BaseEpoxyHolder, prop ->
            holder.view.findViewById(id) as V? ?: throw IllegalStateException("View ID $id for '${prop.name}' not found.")
        }

    /**
     * Taken from Kotterknife
     * https://github.com/JakeWharton/kotterknife
     */
    private class Lazy<V>(
        private val initializer: (BaseEpoxyHolder, KProperty<*>) -> V
    ): ReadOnlyProperty<BaseEpoxyHolder, V> {
        private object EMPTY

        private var value: Any? = EMPTY

        override fun getValue(thisRef: BaseEpoxyHolder, property: KProperty<*>): V {
            if (value == EMPTY) {
                value = initializer(thisRef, property)
            }
            @Suppress("UNCHECKED_CAST")
            return value as V
        }


    }


}
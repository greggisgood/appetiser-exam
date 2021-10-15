package ph.greggjover.appetiserexam.ui.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * This is a [Fragment] extension function which allows automatic handling of ViewBinding lifecycle
 *
 * @param onCleanup - option function which would be called just before the binding object gets cleaned up
 *
 * @return - ViewBinding object
 */
inline fun <reified T : ViewBinding> Fragment.viewBindingLifeCycle(
    noinline onCleanup: ((T) -> Unit)? = null
): ReadWriteProperty<Fragment, T> =
    object : ReadWriteProperty<Fragment, T>, DefaultLifecycleObserver {

        // A backing property to hold our value
        private var binding: T? = null

        init {
            var viewLifecycleOwner: LifecycleOwner? = null
            this@viewBindingLifeCycle
                .viewLifecycleOwnerLiveData
                .observe(this@viewBindingLifeCycle) { newLifecycleOwner ->
                    viewLifecycleOwner?.lifecycle?.removeObserver(this)

                    viewLifecycleOwner = newLifecycleOwner.also {
                        it.lifecycle.addObserver(this)
                    }
                }
        }

        override fun onDestroy(owner: LifecycleOwner) {
            onCleanup?.invoke(binding!!)
            binding = null
        }

        override fun getValue(
            thisRef: Fragment,
            property: KProperty<*>
        ): T {
            return this.binding!!
        }

        override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
            this.binding = value
        }
    }
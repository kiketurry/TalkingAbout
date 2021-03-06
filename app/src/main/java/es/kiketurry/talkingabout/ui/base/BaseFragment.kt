package es.kiketurry.talkingabout.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel

abstract class BaseFragment<B : ViewBinding> : Fragment() {
    abstract val TAG: String?

    var binding: B? = null

    lateinit var baseActivity: BaseActivity<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = activity as BaseActivity<*>
    }

    override fun onResume() {
        super.onResume()
        configureToolbar()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        inflateBinding()
        createViewAfterInflateBinding(inflater, container, savedInstanceState)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        observeViewModel()
        viewCreatedAfterSetupObserverViewModel(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun showToolbar(show: Boolean) {
        baseActivity.showToolbar(show)
    }

    fun showLoading(show: Boolean) {
        baseActivity.showLoading(show)
    }

    fun showError(errorModel: ErrorModel) {
        baseActivity.showError(errorModel)
    }

    fun showDialogFragment(dialogFragment: DialogFragment, tag: String) {
        baseActivity.showDialogFragment(dialogFragment, tag)
    }

    abstract fun inflateBinding()
    abstract fun createViewAfterInflateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    abstract fun setupViewModel()
    abstract fun observeViewModel()
    abstract fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?)
    abstract fun configureToolbar()
}
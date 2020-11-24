package es.kiketurry.talkingabout.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel

abstract class BaseFragment<B : ViewBinding> : Fragment() {
    abstract val TAG: String?

    protected var binding: B? = null

    protected lateinit var baseActivity: BaseActivity<*>

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
        createView(inflater, container, savedInstanceState)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun hideToolbar() {
        baseActivity.hideToolbar()
    }

    fun showToolbar() {
        baseActivity.showToolbar()
    }

    fun showLoading(show: Boolean) {
        baseActivity.showLoading(show)
    }

    fun showError(errorModel: ErrorModel){
        baseActivity.showError(errorModel)
    }

    abstract fun setupViewModel()
    abstract fun observeViewModel()
    abstract fun inflateBinding()
    abstract fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)

    abstract fun configureToolbar()
}
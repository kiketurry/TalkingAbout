package es.kiketurry.talkingabout.ui.base

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel
import es.kiketurry.talkingabout.extensions.gone
import es.kiketurry.talkingabout.extensions.visible
import es.kiketurry.talkingabout.ui.dialogfragment.error.ErrorDialogFragment
import es.kiketurry.talkingabout.ui.dialogfragment.error.ErrorDialogFragment.Companion.ERROR_DIALOG_FRAGMENT_TAG
import es.kiketurry.talkingabout.ui.dialogfragment.loading.LoadingDialogFragment
import es.kiketurry.talkingabout.ui.dialogfragment.loading.LoadingDialogFragment.Companion.LOADING_DIALOG_FRAGMENT_TAG

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity(), View.OnClickListener {
    abstract val TAG: String?

    lateinit var binding: B

    private var isKeyboardVisible = false
    private var tbToolbar: Toolbar? = null
    private var ibToolbarBack: ImageButton? = null
    private var ibToolbarClose: ImageButton? = null
    private var tvToolbarTitle: TextView? = null

    private var loadingDialogFragment: LoadingDialogFragment = LoadingDialogFragment()
    private var errorDialogFragment: ErrorDialogFragment = ErrorDialogFragment()

    override fun onResume() {
        super.onResume()
        configureToolbar()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateBinding()
        setContentView(binding.root)
        findViewByIdToolbar()
        setListenerKeyboardIsVisible()
        setupViewModel()
        observeViewModel()
        createAfterInflateBindingSetupObserverViewModel(savedInstanceState)
        setListenersClickToolbarButton()
    }

    private fun findViewByIdToolbar() {
        tbToolbar = findViewById(R.id.tbToolbar)
        ibToolbarBack = findViewById(R.id.ibToolbarBack)
        ibToolbarClose = findViewById(R.id.ibToolbarClose)
        tvToolbarTitle = findViewById(R.id.tvToolbarTitle)
    }

    private fun setListenerKeyboardIsVisible() {
        val rootViewLayout = window.decorView.rootView
        rootViewLayout.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            rootViewLayout.getWindowVisibleDisplayFrame(r)
            val screenHeight = rootViewLayout.rootView.height
            // r.bottom is the position above soft keypad or device button.
            // if keypad is shown, the r.bottom is smaller than that before.
            val keypadHeight = screenHeight - r.bottom
            //Log.d(TAG, "l> keypadHeight = $keypadHeight")
            if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                // keyboard is opened
                if (!isKeyboardVisible) {
                    isKeyboardVisible = true
                }
            } else {
                // keyboard is closed
                if (isKeyboardVisible) {
                    isKeyboardVisible = false
                }
            }
        }
    }

    private fun setListenersClickToolbarButton() {
        ibToolbarClose?.setOnClickListener(this)
        ibToolbarBack?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.ibToolbarClose -> clickToolbarClose()
            R.id.ibToolbarBack -> clickToolbarBack()
        }
    }

    protected open fun clickToolbarBack() {
        onBackPressed()
    }

    protected open fun clickToolbarClose() {
        finish()
    }

    fun popAllPreviousFragments() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            for (i in 0 until supportFragmentManager.backStackEntryCount) {
                supportFragmentManager.popBackStack()
            }
        }
    }

    fun goBackNumberSteps(steps: Int) {
        var counter = steps
        if (supportFragmentManager.fragments.size > 0 && supportFragmentManager.fragments.size >= steps) {
            while (counter > 0) {
                counter--
                onBackPressed()
            }
        }
    }

    fun isKeyboardVisible(): Boolean {
        return isKeyboardVisible
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, 0)
        }
    }

    fun showToolbar(show: Boolean) {
        tbToolbar?.visible(show)
    }

    fun showBackToolbar(show: Boolean) {
        ibToolbarBack?.visible(show)
    }

    fun showCloseToolbar(show: Boolean) {
        ibToolbarClose?.visible(show)
    }

    fun showTitleToolbar(title: String) {
        tvToolbarTitle?.visible()
        tvToolbarTitle?.text = title
    }

    fun showTitleToolbar(title: Int) {
        tvToolbarTitle?.visible()
        tvToolbarTitle?.text = getString(title)
    }

    fun hideTitleToolbar() {
        tvToolbarTitle?.gone()
    }

    fun showLoading(show: Boolean) {
        if (show) {
            if (supportFragmentManager.findFragmentByTag(LOADING_DIALOG_FRAGMENT_TAG) == null) {
                loadingDialogFragment.show(supportFragmentManager, LOADING_DIALOG_FRAGMENT_TAG)
            }
        } else {
            if (supportFragmentManager.findFragmentByTag(LOADING_DIALOG_FRAGMENT_TAG) != null) {
                loadingDialogFragment.dismiss()
            }
        }
    }

    fun showError(errorModel: ErrorModel) {
        if (supportFragmentManager.findFragmentByTag(ERROR_DIALOG_FRAGMENT_TAG) == null) {
            errorDialogFragment.show(supportFragmentManager, ERROR_DIALOG_FRAGMENT_TAG)
            errorDialogFragment.setError(errorModel)
        } else {
            errorDialogFragment.setErrorAndRefreshInfo(errorModel)
        }
    }

    fun showDialogFragment(dialogFragment: DialogFragment, tag: String) {
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            dialogFragment.show(supportFragmentManager, tag)
        }
    }

    abstract fun inflateBinding()
    abstract fun setupViewModel()
    abstract fun observeViewModel()
    abstract fun createAfterInflateBindingSetupObserverViewModel(savedInstanceState: Bundle?)
    abstract fun configureToolbar()
}
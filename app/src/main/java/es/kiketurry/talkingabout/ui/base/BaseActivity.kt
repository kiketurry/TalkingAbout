package es.kiketurry.talkingabout.ui.base

import android.graphics.Rect
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel
import es.kiketurry.talkingabout.ui.dialogfragment.error.ErrorDialogFragment
import es.kiketurry.talkingabout.ui.dialogfragment.error.ErrorDialogFragment.Companion.ERROR_DIALOG_FRAGMENT_TAG
import es.kiketurry.talkingabout.ui.dialogfragment.loading.LoadingDialogFragment
import es.kiketurry.talkingabout.ui.dialogfragment.loading.LoadingDialogFragment.Companion.LOADING_DIALOG_FRAGMENT_TAG

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {
    abstract val TAG: String?

    protected lateinit var binding: B

    private var isKeyboardVisible = false
    private var tbToolbar: Toolbar? = null
    private var ibToolbarBack: ImageButton? = null
    private var ibToolbarClose: ImageButton? = null
    private var tvToolbarTitle: TextView? = null
    private var loadingDialogFragment: LoadingDialogFragment = LoadingDialogFragment()
    private var errorDialogFragment: ErrorDialogFragment = ErrorDialogFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateBinding()
        setContentView(binding.root)
        findViewByIdToolbar()
        setListenerKeyboardIsVisible()
        setupViewModel()
        observeViewModel()
        create(savedInstanceState)

        ibToolbarClose?.setOnClickListener {
            clickToolbarClose()
        }

        ibToolbarBack?.setOnClickListener {
            clickToolbarBack()
        }
    }

    private fun findViewByIdToolbar() {
        tbToolbar = findViewById(R.id.tbToolbar)
        ibToolbarBack = findViewById(R.id.ibToolbarBack)
        ibToolbarClose = findViewById(R.id.ibToolbarClose)
        tvToolbarTitle = findViewById(R.id.tvToolbarTitle)
    }

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

    fun hideToolbar() {
        tbToolbar?.visibility = GONE
    }

    fun showToolbar() {
        tbToolbar?.visibility = VISIBLE
    }

    fun hideBackToolbar() {
        ibToolbarBack?.visibility = GONE
    }

    fun showBackToolbar() {
        ibToolbarBack?.visibility = VISIBLE
    }

    fun hideCloseToolbar() {
        ibToolbarClose?.visibility = GONE
    }

    fun showCloseToolbar() {
        ibToolbarClose?.visibility = VISIBLE
    }

    fun hideTitleToolbar() {
        tvToolbarTitle?.visibility = GONE
    }

    fun showTitleToolbar(title: String) {
        tvToolbarTitle?.text = title
        tvToolbarTitle?.visibility = VISIBLE
    }

    fun showTitleToolbar(title: Int) {
        tvToolbarTitle?.text = getString(title)
        tvToolbarTitle?.visibility = VISIBLE
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

    protected open fun clickToolbarBack() {
        onBackPressed()
    }

    protected open fun clickToolbarClose() {
        finish()
    }

    abstract fun inflateBinding()

    abstract fun setupViewModel()

    abstract fun observeViewModel()

    abstract fun create(savedInstanceState: Bundle?)

    abstract fun configureToolbar()

}
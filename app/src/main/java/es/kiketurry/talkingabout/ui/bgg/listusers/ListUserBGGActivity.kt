package es.kiketurry.talkingabout.ui.bgg.listusers

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.databinding.ActivityListUsersBggBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseActivity

class ListUserBGGActivity : BaseActivity<ActivityListUsersBggBinding>() {
    override val TAG: String? get() = ListUserBGGActivity::class.qualifiedName

    lateinit var listUsersBGGViewModel: ListUsersBGGViewModel

    override fun inflateBinding() {
        binding = ActivityListUsersBggBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        listUsersBGGViewModel =
            ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(this)).get(ListUsersBGGViewModel::class.java)
    }

    override fun observeViewModel() = Unit

    override fun createAfterInflateBindingSetupObserverViewModel(savedInstanceState: Bundle?) {
        binding.clToolbar.ibToolbarClose.setOnClickListener { Log.i(TAG, "Pulsamos Cerrar") }
    }

    override fun configureToolbar() {
        showTitleToolbar(getString(R.string.toolbarListUsersBGG))
        showBackToolbar(false)
        showCloseToolbar(true)
    }
}
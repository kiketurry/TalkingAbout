package es.kiketurry.talkingabout.ui.bgg

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.data.constants.IntentKeys.Companion.INTENT_KEY_EDIT_USER_BGG
import es.kiketurry.talkingabout.data.domain.model.bgg.UserBGGModel
import es.kiketurry.talkingabout.databinding.ActivityBggBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseActivity
import es.kiketurry.talkingabout.ui.bgg.addmodifyuser.AddModifyUserBGGFragment
import es.kiketurry.talkingabout.ui.bgg.listusers.ListUserBGGFragment

class BGGActivity : BaseActivity<ActivityBggBinding>() {
    override val TAG: String? get() = BGGActivity::class.qualifiedName

    lateinit var bggViewModel: BGGViewModel

    override fun inflateBinding() {
        binding = ActivityBggBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        bggViewModel =
            ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(this)).get(BGGViewModel::class.java)
    }

    override fun observeViewModel() {
        bggViewModel.loadingMutableLiveData.observe(this, this::showLoading)
        bggViewModel.errorMutableLiveData.observe(this, this::showError)
    }

    override fun createAfterInflateBindingSetupObserverViewModel(savedInstanceState: Bundle?) {
        goToListUserFragment()
    }

    private fun goToListUserFragment() {
        val fragment: Fragment = ListUserBGGFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.flMainFragmentHost, fragment, ListUserBGGFragment::class.qualifiedName)
            .addToBackStack(ListUserBGGFragment::class.qualifiedName)
            .commit()
    }

    fun goToAddUser(edit: Boolean = false, userBGGModel: UserBGGModel = UserBGGModel()) {
        val fragment: Fragment = AddModifyUserBGGFragment()
        if (edit) {
            val editBundle = Bundle()
            editBundle.putSerializable(INTENT_KEY_EDIT_USER_BGG, userBGGModel)
            fragment.arguments = editBundle
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.flMainFragmentHost, fragment, AddModifyUserBGGFragment::class.qualifiedName)
            .addToBackStack(ListUserBGGFragment::class.qualifiedName)
            .commit()
    }

    override fun configureToolbar() {
        showTitleToolbar(getString(R.string.toolbarBGG))
        showBackToolbar(false)
        showCloseToolbar(true)
    }
}
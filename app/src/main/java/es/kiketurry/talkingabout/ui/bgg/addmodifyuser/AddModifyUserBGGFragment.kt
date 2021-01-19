package es.kiketurry.talkingabout.ui.bgg.addmodifyuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.data.constants.IntentKeys.Companion.INTENT_KEY_EDIT_USER_BGG
import es.kiketurry.talkingabout.data.domain.model.bgg.UserBGGModel
import es.kiketurry.talkingabout.databinding.FragmentBggAddUsersBinding
import es.kiketurry.talkingabout.extensions.toast
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseFragment
import es.kiketurry.talkingabout.ui.bgg.BGGActivity

class AddModifyUserBGGFragment : BaseFragment<FragmentBggAddUsersBinding>() {
    override val TAG: String? get() = AddModifyUserBGGFragment::class.qualifiedName

    lateinit var addModifyUsersBGGViewModel: AddModifyUsersBGGViewModel

    override fun inflateBinding() {
        binding = FragmentBggAddUsersBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        addModifyUsersBGGViewModel = ViewModelProvider(
            this,
            InjectionSingleton.provideViewModelFactory(requireContext())
        ).get(AddModifyUsersBGGViewModel::class.java)
    }

    override fun observeViewModel() = Unit

    override fun configureToolbar() {
        baseActivity.showTitleToolbar(getString(R.string.toolbarListUsersBGG))
        baseActivity.showBackToolbar(false)
        baseActivity.showCloseToolbar(true)
    }

    override fun createViewAfterInflateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        binding?.btAddUser?.setOnClickListener {
            if (binding?.etUserBGG?.text.isNullOrBlank()) {
                toast(getString(R.string.bggAddUserLimitMinUsername))
            } else {
                addModifyUsersBGGViewModel.manageUserBGG(
                    binding?.etUserBGG?.text.toString(),
                    binding?.etName?.text.toString(),
                    binding?.etEmail?.text.toString(),
                    binding?.etPrefix?.text.toString(),
                    binding?.etPhone?.text.toString()
                )
                (baseActivity as BGGActivity).onBackPressed()
            }
        }
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {
        if (arguments != null && arguments!!.containsKey(INTENT_KEY_EDIT_USER_BGG)) {
            addModifyUsersBGGViewModel.modify = true
            val editUserBGG: UserBGGModel = arguments!!.get(INTENT_KEY_EDIT_USER_BGG) as UserBGGModel
            binding?.etUserBGG?.setText(editUserBGG.userBGG)
            binding?.etUserBGG?.isEnabled = false
            binding?.etName?.setText(editUserBGG.name)
            binding?.etEmail?.setText(editUserBGG.email)
            binding?.etPrefix?.setText(editUserBGG.prefix)
            binding?.etPhone?.setText(editUserBGG.phone)
        }
    }
}
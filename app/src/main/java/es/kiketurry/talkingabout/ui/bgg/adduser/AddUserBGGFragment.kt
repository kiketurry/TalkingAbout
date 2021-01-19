package es.kiketurry.talkingabout.ui.bgg.adduser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.databinding.FragmentBggAddUsersBinding
import es.kiketurry.talkingabout.extensions.toast
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseFragment

class AddUserBGGFragment : BaseFragment<FragmentBggAddUsersBinding>() {
    override val TAG: String? get() = AddUserBGGFragment::class.qualifiedName

    lateinit var addUsersBGGViewModel: AddUsersBGGViewModel

    override fun inflateBinding() {
        binding = FragmentBggAddUsersBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        addUsersBGGViewModel =
            ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(requireContext())).get(AddUsersBGGViewModel::class.java)
    }

    override fun observeViewModel() = Unit

    override fun configureToolbar() {
        baseActivity.showTitleToolbar(getString(R.string.toolbarListUsersBGG))
        baseActivity.showBackToolbar(false)
        baseActivity.showCloseToolbar(true)
    }

    override fun createViewAfterInflateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        binding?.btAddUser?.setOnClickListener(View.OnClickListener {
            if (binding?.etUserBGG?.text.isNullOrBlank()) {
                toast(getString(R.string.bggAddUserLimitMinUsername))
            } else {
                addUsersBGGViewModel.addUserBGG(
                    binding?.etUserBGG?.text.toString(),
                    binding?.etName?.text.toString(),
                    binding?.etEmail?.text.toString(),
                    binding?.etPrefix?.text.toString(),
                    binding?.etPhone?.text.toString()
                )

            }
        })

    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {

    }
}
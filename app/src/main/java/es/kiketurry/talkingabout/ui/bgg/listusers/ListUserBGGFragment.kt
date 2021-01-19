package es.kiketurry.talkingabout.ui.bgg.listusers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.databinding.FragmentBggListUsersBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseFragment
import es.kiketurry.talkingabout.ui.bgg.BGGActivity

class ListUserBGGFragment : BaseFragment<FragmentBggListUsersBinding>() {
    override val TAG: String? get() = ListUserBGGFragment::class.qualifiedName

    lateinit var listUsersBGGViewModel: ListUsersBGGViewModel

    override fun inflateBinding() {
        binding = FragmentBggListUsersBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        listUsersBGGViewModel =
            ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(requireContext())).get(ListUsersBGGViewModel::class.java)
    }

    override fun observeViewModel() {
        listUsersBGGViewModel.usersListMutableLiveData.observe(this, Observer { listUsersBGGViewModel ->
            listUsersBGGViewModel.forEachIndexed { index, userBGGRoomEntity ->
                Log.i(TAG, "l> tenemos al usuario ${userBGGRoomEntity.name} con nick ${userBGGRoomEntity.userBGG}")
            }
        })
    }

    override fun configureToolbar() {
        baseActivity.showTitleToolbar(getString(R.string.toolbarListUsersBGG))
        baseActivity.showBackToolbar(false)
        baseActivity.showCloseToolbar(true)
    }

    override fun createViewAfterInflateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        binding?.fabAddUserBGG?.setOnClickListener {
            (baseActivity as BGGActivity).goToAddUser()
        }
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {

    }
}
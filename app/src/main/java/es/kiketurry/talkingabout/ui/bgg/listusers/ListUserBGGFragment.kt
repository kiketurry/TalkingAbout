package es.kiketurry.talkingabout.ui.bgg.listusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.data.domain.model.bgg.UserBGGModel
import es.kiketurry.talkingabout.databinding.FragmentBggListUsersBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseFragment
import es.kiketurry.talkingabout.ui.bgg.BGGActivity
import es.kiketurry.talkingabout.ui.bgg.listusers.adapter.UsersBGGAdapter
import java.util.*

class ListUserBGGFragment : BaseFragment<FragmentBggListUsersBinding>(), UsersBGGAdapter.ItemUserBGGClickListener {
    override val TAG: String? get() = ListUserBGGFragment::class.qualifiedName

    lateinit var listUsersBGGViewModel: ListUsersBGGViewModel

    private lateinit var usersBGGAdapter: UsersBGGAdapter

    override fun inflateBinding() {
        binding = FragmentBggListUsersBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        listUsersBGGViewModel =
            ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(requireContext())).get(ListUsersBGGViewModel::class.java)
    }

    override fun observeViewModel() {
        listUsersBGGViewModel.errorMutableLiveData.observe(viewLifecycleOwner, this::showError)
        listUsersBGGViewModel.loadingMutableLiveData.observe(viewLifecycleOwner, this::showLoading)

        listUsersBGGViewModel.usersListMutableLiveData.observe(viewLifecycleOwner, Observer { listUserBGG ->
            usersBGGAdapter.refreshUsers(listUserBGG)
        })
    }

    override fun configureToolbar() {
        baseActivity.showTitleToolbar(getString(R.string.toolbarListUsersBGG))
        baseActivity.showBackToolbar(false)
        baseActivity.showCloseToolbar(true)
    }

    override fun createViewAfterInflateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        usersBGGAdapter = UsersBGGAdapter(context!!, ArrayList(), this)
        binding?.rvListUsersBGG?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = usersBGGAdapter
        }

        binding?.fabAddUserBGG?.setOnClickListener {
            (baseActivity as BGGActivity).goToAddUser()
        }
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {
        listUsersBGGViewModel.observeUsersBGGBBDD(viewLifecycleOwner)
    }

    override fun onItemUserBGGClick(userBGGModel: UserBGGModel) {
        (baseActivity as BGGActivity).setUserBGGSelected(userBGGModel.userBGG)
    }

    override fun onEditUserBGGClick(userBGGModel: UserBGGModel) {
        (baseActivity as BGGActivity).goToAddUser(true, userBGGModel)
    }

    override fun onDeleteUserBGGClick(userBGGModel: UserBGGModel) {
        listUsersBGGViewModel.deleteUserBGG(userBGGModel)
    }
}
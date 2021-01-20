package es.kiketurry.talkingabout.ui.bgg.listboardgames

import android.os.Bundle
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
import es.kiketurry.talkingabout.ui.bgg.listusers.adapter.UsersBGGAdapter

class ListBoardGamesBGGFragment : BaseFragment<FragmentBggListUsersBinding>() {
    override val TAG: String? get() = ListBoardGamesBGGFragment::class.qualifiedName

    lateinit var listBoardGamesBGGViewModel: ListBoardGamesBGGViewModel

    private lateinit var usersBGGAdapter: UsersBGGAdapter

    override fun inflateBinding() {
        binding = FragmentBggListUsersBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        listBoardGamesBGGViewModel =
            ViewModelProvider(
                this,
                InjectionSingleton.provideViewModelFactory(requireContext())
            ).get(ListBoardGamesBGGViewModel::class.java)
    }

    override fun observeViewModel() {
        listBoardGamesBGGViewModel.errorMutableLiveData.observe(viewLifecycleOwner, this::showError)
        listBoardGamesBGGViewModel.loadingMutableLiveData.observe(viewLifecycleOwner, this::showLoading)

        (baseActivity as BGGActivity).bggViewModel.userBGGSelectedMutableLiveData.observe(
            viewLifecycleOwner,
            Observer { userSelectedBGG -> userSelectedBGG(userSelectedBGG) })
    }

    private fun userSelectedBGG(userSelectedBGG: String) {
        listBoardGamesBGGViewModel.userSelectedBGG(userSelectedBGG)
    }

    override fun configureToolbar() {
        baseActivity.showTitleToolbar(getString(R.string.toolbarListBoardGamesBGG))
        baseActivity.showBackToolbar(false)
        baseActivity.showCloseToolbar(false)
    }

    override fun createViewAfterInflateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {
    }
}
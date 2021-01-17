package es.kiketurry.talkingabout.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.databinding.FragmentDetailCatMotionLayoutPhotosBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.MainActivity
import es.kiketurry.talkingabout.ui.base.BaseFragment
import es.kiketurry.talkingabout.ui.detail.adapter.DetailCatPhotosAdapter
import es.kiketurry.talkingabout.ui.detail.viewmodel.DetailCatViewModel
import java.util.*

class DetailCatFragmentMotionLayoutPhotos : BaseFragment<FragmentDetailCatMotionLayoutPhotosBinding>(),
    DetailCatPhotosAdapter.ItemPhotoClickListener {
    override val TAG: String? get() = DetailCatFragmentMotionLayoutPhotos::class.qualifiedName

    lateinit var detailCatViewModel: DetailCatViewModel

    private lateinit var detailCatPhotosAdapter: DetailCatPhotosAdapter

    override fun setupViewModel() {
        detailCatViewModel = ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(context!!)).get(DetailCatViewModel::class.java)
    }

    override fun observeViewModel() {
        detailCatViewModel.errorMutableLiveData.observe(this, this::showError)
        detailCatViewModel.loadingMutableLiveData.observe(this, this::showLoading)

        (baseActivity as MainActivity).mainViewModel.breedCatSelectedMutableLiveData.observe(this, Observer { breedModel ->
            detailCatPhotosAdapter.refreshPhotos(breedModel.photoList)
        })
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) = Unit

    override fun inflateBinding() {
        binding = FragmentDetailCatMotionLayoutPhotosBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        detailCatPhotosAdapter = DetailCatPhotosAdapter(context!!, ArrayList(), this)
        binding?.rvPhotos?.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = detailCatPhotosAdapter
        }
    }

    override fun configureToolbar() {
        baseActivity.showBackToolbar(true)
        baseActivity.showTitleToolbar(R.string.toolbar_title_fragment_detail_cat)
        baseActivity.showCloseToolbar(true)
    }

    override fun onItemPhotoClick(position: Int) {
        Log.i(TAG, "l> Hemos pulsado en un lindo gatito")
    }
}
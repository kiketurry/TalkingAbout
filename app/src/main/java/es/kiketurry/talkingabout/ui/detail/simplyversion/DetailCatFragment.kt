package es.kiketurry.talkingabout.ui.detail.simplyversion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.databinding.FragmentDetailCatBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.MainActivity
import es.kiketurry.talkingabout.ui.base.BaseFragment
import es.kiketurry.talkingabout.ui.detail.adapter.DetailCatPhotosAdapter
import es.kiketurry.talkingabout.ui.detail.viewmodel.DetailCatViewModel
import java.util.*

class DetailCatFragment : BaseFragment<FragmentDetailCatBinding>(), DetailCatPhotosAdapter.ItemPhotoClickListener {
    override val TAG: String? get() = DetailCatFragment::class.qualifiedName

    lateinit var detailCatViewModel : DetailCatViewModel

    private lateinit var detailCatPhotosAdapter: DetailCatPhotosAdapter

    override fun setupViewModel() {
        detailCatViewModel = ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(context!!)).get(DetailCatViewModel::class.java)
    }

    override fun observeViewModel() {
        detailCatViewModel.errorMutableLiveData.observe(this, this::showError)
        detailCatViewModel.loadingMutableLiveData.observe(this, this::showLoading)

        (baseActivity as MainActivity).mainViewModel.breedCatSelectedMutableLiveData.observe(this, Observer { breedModel ->
            binding?.tvBreed?.text = breedModel.name
            binding?.tvDescription?.text = breedModel.description
            binding?.tvAdaptability?.text = breedModel.adaptability.toString()
            binding?.tvIntelligence?.text = breedModel.intelligence.toString()
            binding?.tvAffectionLevel?.text = breedModel.affectionLevel.toString()
            binding?.tvChildFriendly?.text = breedModel.childFriendly.toString()
            binding?.tvDogFriendly?.text = breedModel.dogFriendly.toString()

            detailCatPhotosAdapter.refreshPhotos(breedModel.photoList)
        })
    }

    override fun inflateBinding() {
        binding = FragmentDetailCatBinding.inflate(layoutInflater)
    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        detailCatPhotosAdapter = DetailCatPhotosAdapter(context!!, ArrayList(), this)
        binding?.rvPhotos?.apply{
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
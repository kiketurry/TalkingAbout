package es.kiketurry.talkingabout.ui.cats.detail.simplyversion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.databinding.FragmentCatDetailBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseFragment
import es.kiketurry.talkingabout.ui.cats.CatsActivity
import es.kiketurry.talkingabout.ui.cats.detail.adapter.DetailCatPhotosAdapter
import es.kiketurry.talkingabout.ui.cats.detail.viewmodel.DetailCatViewModel
import java.util.*

class DetailCatFragment : BaseFragment<FragmentCatDetailBinding>(), DetailCatPhotosAdapter.ItemPhotoClickListener {
    override val TAG: String? get() = DetailCatFragment::class.qualifiedName

    lateinit var detailCatViewModel: DetailCatViewModel

    private lateinit var detailCatPhotosAdapter: DetailCatPhotosAdapter

    override fun setupViewModel() {
        detailCatViewModel =
            ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(context!!)).get(DetailCatViewModel::class.java)
    }

    override fun observeViewModel() {
        detailCatViewModel.errorMutableLiveData.observe(viewLifecycleOwner, this::showError)
        detailCatViewModel.loadingMutableLiveData.observe(viewLifecycleOwner, this::showLoading)

        (baseActivity as CatsActivity).catsViewModel.breedCatSelectedMutableLiveData.observe(viewLifecycleOwner, Observer { breedModel ->
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

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) = Unit

    override fun inflateBinding() {
        binding = FragmentCatDetailBinding.inflate(layoutInflater)
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
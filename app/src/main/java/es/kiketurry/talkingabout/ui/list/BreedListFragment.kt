package es.kiketurry.talkingabout.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.databinding.FragmentBreedsListBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.MainActivity
import es.kiketurry.talkingabout.ui.base.BaseFragment
import es.kiketurry.talkingabout.ui.list.adapter.BreedsAdapter
import java.util.ArrayList

class BreedListFragment : BaseFragment<FragmentBreedsListBinding>(), BreedsAdapter.ItemBreedClickListener {
    override val TAG: String? get() = BreedListFragment::class.qualifiedName

    lateinit var breedListViewmodel : BreedListViewModel

    private lateinit var breedsAdapter: BreedsAdapter

    override fun setupViewModel() {
        breedListViewmodel = ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(context!!)).get(BreedListViewModel::class.java)
    }

    override fun observeViewModel() {
        breedListViewmodel.errorMutableLiveData.observe(this, this::showError)
        breedListViewmodel.loadingMutableLiveData.observe(this, this::showLoading)

        (baseActivity as MainActivity).mainViewModel.breedsModelListMutableLiveData.observe(this, Observer { breedModelList ->
            Log.d(TAG, "l> breedsModelListMutableLiveData posted")
            breedsAdapter.refreshBreeds(breedListViewmodel.getNamesBreedList(breedModelList))
        })
    }

    override fun inflateBinding() {
        binding = FragmentBreedsListBinding.inflate(layoutInflater)
    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        breedsAdapter = BreedsAdapter(context!!, ArrayList(), this)
        binding?.rvBreeds?.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = breedsAdapter
        }
    }

    override fun configureToolbar() {
        baseActivity.hideBackToolbar()
        baseActivity.showTitleToolbar(R.string.toolbar_title_fragment_breeds_list)
        baseActivity.showCloseToolbar()
    }

    override fun onItemBreedClick(position: Int) {
        (baseActivity as MainActivity).setPositionBreedSelected(position)
    }
}
package es.kiketurry.talkingabout.ui.cats.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.databinding.FragmentCatBreedsListBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseFragment
import es.kiketurry.talkingabout.ui.cats.CatsActivity
import es.kiketurry.talkingabout.ui.cats.list.adapter.BreedsAdapter
import es.kiketurry.talkingabout.ui.dialogfragment.info.InfoDialogFragment
import es.kiketurry.talkingabout.ui.dialogfragment.info.InfoDialogFragment.Companion.INFO_DIALOG_FRAGMENT_TAG
import java.util.*

class BreedListFragment : BaseFragment<FragmentCatBreedsListBinding>(), BreedsAdapter.ItemBreedClickListener,
    InfoDialogFragment.InfoDialogFragmentClickButtonListener {
    override val TAG: String? get() = BreedListFragment::class.qualifiedName

    lateinit var breedListViewmodel: BreedListViewModel

    private lateinit var breedsAdapter: BreedsAdapter

    override fun setupViewModel() {
        breedListViewmodel =
            ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(context!!)).get(BreedListViewModel::class.java)
    }

    override fun observeViewModel() {
        breedListViewmodel.errorMutableLiveData.observe(viewLifecycleOwner, this::showError)
        breedListViewmodel.loadingMutableLiveData.observe(viewLifecycleOwner, this::showLoading)

        (baseActivity as CatsActivity).catsViewModel.breedsModelListMutableLiveData.observe(viewLifecycleOwner, Observer { breedModelList ->
            Log.d(TAG, "l> breedsModelListMutableLiveData posted")
            breedsAdapter.refreshBreeds(breedListViewmodel.getNamesBreedList(breedModelList))
        })
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) = Unit

    override fun inflateBinding() {
        binding = FragmentCatBreedsListBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        breedsAdapter = BreedsAdapter(context!!, ArrayList(), this)
        binding?.rvBreeds?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = breedsAdapter
        }
    }

    private fun launchDialogFragment() {
        var infoDialogFragment: InfoDialogFragment = InfoDialogFragment()
        infoDialogFragment.setValue(
            this,
            "Hola y Adios",
            "Caracola del infierno",
            ResourcesCompat.getDrawable(resources, R.drawable.ic_engine, null),
            getString(R.string.add),
            getString(R.string.app_name)
        )
        showDialogFragment(infoDialogFragment, INFO_DIALOG_FRAGMENT_TAG)
    }

    override fun configureToolbar() {
        baseActivity.showBackToolbar(false)
        baseActivity.showTitleToolbar(R.string.toolbar_title_fragment_breeds_list)
        baseActivity.showCloseToolbar(true)
    }

    override fun onItemBreedClick(position: Int) {
        (baseActivity as CatsActivity).setPositionBreedSelected(position)
    }

    override fun onInfoDialogFragmentClickPositive() {
        Log.i(TAG, "l> Hemos pulsado el button del dialog :-)")
    }

    override fun onInfoDialogFragmentClickNegative() {
        Log.i(TAG, "l> Hemos pulsado el button del dialog :-(")
    }
}
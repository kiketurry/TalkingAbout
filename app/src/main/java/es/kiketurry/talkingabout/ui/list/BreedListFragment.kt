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
import es.kiketurry.talkingabout.ui.dialogfragment.TextButtonDialogFragment
import es.kiketurry.talkingabout.ui.dialogfragment.TextButtonDialogFragment.Companion.TEXT_BUTTON_DIALOG_FRAGMENT_TAG
import es.kiketurry.talkingabout.ui.list.adapter.BreedsAdapter
import java.util.*

class BreedListFragment : BaseFragment<FragmentBreedsListBinding>(), BreedsAdapter.ItemBreedClickListener,
    TextButtonDialogFragment.TextButtonDialogFragmentClickButtonListener {
    override val TAG: String? get() = BreedListFragment::class.qualifiedName

    lateinit var breedListViewmodel: BreedListViewModel

    private lateinit var breedsAdapter: BreedsAdapter

    override fun setupViewModel() {
        breedListViewmodel =
            ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(context!!)).get(BreedListViewModel::class.java)
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
        binding?.rvBreeds?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = breedsAdapter
        }

        launchDialogFragment()
    }

    private fun launchDialogFragment() {
        var textButtonDialogFragment: TextButtonDialogFragment = TextButtonDialogFragment()
        textButtonDialogFragment.setValue("Hola y Adios", "Caracola del infierno", this)
        showDialogFragment(textButtonDialogFragment, TEXT_BUTTON_DIALOG_FRAGMENT_TAG)
    }

    override fun configureToolbar() {
        baseActivity.showBackToolbar(false)
        baseActivity.showTitleToolbar(R.string.toolbar_title_fragment_breeds_list)
        baseActivity.showCloseToolbar(true)
    }

    override fun onItemBreedClick(position: Int) {
        (baseActivity as MainActivity).setPositionBreedSelected(position)
    }

    override fun onTextButtonDialogFragmentClickButton() {
        Log.i(TAG, "l> Hemos pulsado el button del dialog :-)")
    }
}
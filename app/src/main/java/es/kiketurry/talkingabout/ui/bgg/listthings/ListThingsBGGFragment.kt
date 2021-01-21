package es.kiketurry.talkingabout.ui.bgg.listthings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel
import es.kiketurry.talkingabout.databinding.FragmentBggListThingsBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseFragment
import es.kiketurry.talkingabout.ui.bgg.BGGActivity
import es.kiketurry.talkingabout.ui.bgg.listthings.adapter.ListThingsBGGAdapter
import es.kiketurry.talkingabout.ui.bgg.listthings.adapter.ListThingsBGGAdapter.ItemThingBGGClickListener
import java.util.*

class ListThingsBGGFragment : BaseFragment<FragmentBggListThingsBinding>(), ItemThingBGGClickListener {
    override val TAG: String? get() = ListThingsBGGFragment::class.qualifiedName

    lateinit var listThingsBGGViewModel: ListThingsBGGViewModel

    private lateinit var listThingsBGGAdapter: ListThingsBGGAdapter

    override fun inflateBinding() {
        binding = FragmentBggListThingsBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        listThingsBGGViewModel =
            ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(requireContext())).get(ListThingsBGGViewModel::class.java)
    }

    override fun observeViewModel() {
        listThingsBGGViewModel.errorMutableLiveData.observe(viewLifecycleOwner, this::showError)
        listThingsBGGViewModel.loadingMutableLiveData.observe(viewLifecycleOwner, this::showLoading)

        (baseActivity as BGGActivity).bggViewModel.userBGGSelectedMutableLiveData.observe(
            viewLifecycleOwner,
            Observer { userSelectedBGG -> userSelectedBGG(userSelectedBGG) })
    }

    private fun userSelectedBGG(userSelectedBGG: String) {
        listThingsBGGViewModel.userSelectedBGG(userSelectedBGG)
    }

    override fun configureToolbar() {
        baseActivity.showTitleToolbar(getString(R.string.toolbarListBoardGamesBGG))
        baseActivity.showBackToolbar(false)
        baseActivity.showCloseToolbar(false)
    }

    override fun createViewAfterInflateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        listThingsBGGAdapter = ListThingsBGGAdapter(context!!, ArrayList(), this)
        binding?.rvListThingsBGG?.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = listThingsBGGAdapter
        }
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {
    }

    override fun onItemThingClick(position: ThingBGGModel) {
        Log.i(TAG, "l> Hemos pulsado en una linda cosa")
    }

    override fun onWhatsappThingClick(position: ThingBGGModel) {
        Log.i(TAG, "l> Hemos pulsado para enviar whatsapp")
    }
}
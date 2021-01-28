package es.kiketurry.talkingabout.ui.bgg.listthings

import android.os.Bundle
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
import es.kiketurry.talkingabout.utils.WhatsappUtils
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

        listThingsBGGViewModel.listShowThingBGGModelMutableLiveData.observe(viewLifecycleOwner, { listThingsBGGViewModel ->
            listThingsBGGAdapter.refreshThings(listThingsBGGViewModel)
        })

        listThingsBGGViewModel.listTotalBoardGamesMutableLiveData.observe(viewLifecycleOwner, { count ->
            binding?.clConfigListThings?.tvTotalBoardGamesData?.text = count.toString()
        })

        listThingsBGGViewModel.listTotalBoardGamesBasicMutableLiveData.observe(viewLifecycleOwner, { count ->
            binding?.clConfigListThings?.tvTotalBoardGamesBasicData?.text = count.toString()
        })

        listThingsBGGViewModel.listTotalBoardGamesExpansionMutableLiveData.observe(viewLifecycleOwner, { count ->
            binding?.clConfigListThings?.tvTotalExpansionData?.text = count.toString()
        })
    }

    private fun userSelectedBGG(userSelectedBGG: String) {
        listThingsBGGViewModel.observeThingsUser(viewLifecycleOwner, userSelectedBGG)
    }

    override fun configureToolbar() {
        baseActivity.showTitleToolbar(getString(R.string.toolbarListBoardGamesBGG))
        baseActivity.showBackToolbar(true)
        baseActivity.showCloseToolbar(false)
    }

    override fun createViewAfterInflateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        listThingsBGGAdapter = ListThingsBGGAdapter(context!!, ArrayList(), this)

        binding?.rvListThingsBGG?.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = listThingsBGGAdapter
        }

        binding?.clConfigListThings?.ivOpenClose?.setOnClickListener {
            binding?.mlListThings?.currentState.let {
                if (it == R.id.stateCloseConfig) {
                    binding?.mlListThings?.transitionToEnd()
                } else {
                    binding?.mlListThings?.transitionToStart()
                }
            }
        }
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {
    }

    override fun onItemThingClick(thingBGGModel: ThingBGGModel) {
        (baseActivity as BGGActivity).setThingBGGSelected(thingBGGModel)
    }

    override fun onWhatsappThingClick(thingBGGModel: ThingBGGModel) {
        WhatsappUtils.sendWhatsapp(baseActivity, thingBGGModel.bestName())
    }
}
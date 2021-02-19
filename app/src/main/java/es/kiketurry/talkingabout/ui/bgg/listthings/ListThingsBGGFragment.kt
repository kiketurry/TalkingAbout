package es.kiketurry.talkingabout.ui.bgg.listthings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.data.constants.IntentKeys
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel.TypeThingBGG.*
import es.kiketurry.talkingabout.databinding.FragmentBggListThingsBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseActivity
import es.kiketurry.talkingabout.ui.base.BaseFragment
import es.kiketurry.talkingabout.ui.bgg.BGGActivity
import es.kiketurry.talkingabout.ui.bgg.listthings.adapter.ListThingsBGGAdapter
import es.kiketurry.talkingabout.ui.bgg.listthings.adapter.ListThingsBGGAdapter.ItemThingBGGClickListener
import es.kiketurry.talkingabout.ui.calculator.detail.CalculatorDetailActivity
import es.kiketurry.talkingabout.utils.WhatsappUtils
import java.util.*

class ListThingsBGGFragment : BaseFragment<FragmentBggListThingsBinding>(), ItemThingBGGClickListener, View.OnClickListener {
    override val TAG: String? get() = ListThingsBGGFragment::class.qualifiedName

    lateinit var listThingsBGGViewModel: ListThingsBGGViewModel

    private lateinit var listThingsBGGAdapter: ListThingsBGGAdapter

    override fun inflateBinding() {
        binding = FragmentBggListThingsBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        listThingsBGGViewModel = ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(requireContext())).get(ListThingsBGGViewModel::class.java)
    }

    override fun observeViewModel() {
        listThingsBGGViewModel.errorMutableLiveData.observe(viewLifecycleOwner, this::showError)
        listThingsBGGViewModel.loadingMutableLiveData.observe(viewLifecycleOwner, this::showLoading)

        (baseActivity as BGGActivity).bggViewModel.userBGGSelectedMutableLiveData.observe(viewLifecycleOwner, { userSelectedBGG ->
            listThingsBGGViewModel.observeThingsUser(viewLifecycleOwner, userSelectedBGG)
        })

        listThingsBGGViewModel.listShowThingBGGModelMutableLiveData.observe(viewLifecycleOwner, { listThingsBGGViewModel ->
            listThingsBGGAdapter.refreshThings(listThingsBGGViewModel)
        })

        listThingsBGGViewModel.totalBoardGamesMutableLiveData.observe(viewLifecycleOwner, { count ->
            binding?.clConfigListThings?.tvTotalBoardGamesData?.text = count.toString()
        })

        listThingsBGGViewModel.totalBoardGamesBasicMutableLiveData.observe(viewLifecycleOwner, { count ->
            binding?.clConfigListThings?.tvTotalBoardGamesBasicData?.text = count.toString()
        })

        listThingsBGGViewModel.totalBoardGamesExpansionMutableLiveData.observe(viewLifecycleOwner, { count ->
            binding?.clConfigListThings?.tvTotalExpansionData?.text = count.toString()
        })

        listThingsBGGViewModel.typeShowConfigSelectedListMutableLiveData.observe(viewLifecycleOwner, { typeShow ->
            when (typeShow!!) {
                TYPE_THING_UNKNOW -> {
                    binding?.clConfigListThings?.ivCheckTotalBoardGames?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_check))
                    binding?.clConfigListThings?.ivCheckTotalBoardGamesBasic?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_uncheck))
                    binding?.clConfigListThings?.ivCheckTotalExpansion?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_uncheck))
                    (baseActivity as BaseActivity<*>).showTitleToolbar(R.string.toolbar_title_fragment_list_things_all)
                }
                TYPE_THING_BOARDGAME -> {
                    binding?.clConfigListThings?.ivCheckTotalBoardGames?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_uncheck))
                    binding?.clConfigListThings?.ivCheckTotalBoardGamesBasic?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_check))
                    binding?.clConfigListThings?.ivCheckTotalExpansion?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_uncheck))
                    (baseActivity as BaseActivity<*>).showTitleToolbar(R.string.toolbar_title_fragment_list_things_boardgames)
                }
                TYPE_THING_EXPANSION -> {
                    binding?.clConfigListThings?.ivCheckTotalBoardGames?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_uncheck))
                    binding?.clConfigListThings?.ivCheckTotalBoardGamesBasic?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_uncheck))
                    binding?.clConfigListThings?.ivCheckTotalExpansion?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_check))
                    (baseActivity as BaseActivity<*>).showTitleToolbar(R.string.toolbar_title_fragment_list_things_expansions)
                }
            }
        })
    }

    override fun configureToolbar() {
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

        binding?.clConfigListThings?.ivCheckTotalBoardGames?.setOnClickListener(this)
        binding?.clConfigListThings?.ivCheckTotalBoardGamesBasic?.setOnClickListener(this)
        binding?.clConfigListThings?.ivCheckTotalExpansion?.setOnClickListener(this)
        binding?.btLaunchIntent?.setOnClickListener(this)
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) = Unit

    override fun onItemThingClick(thingBGGModel: ThingBGGModel) {
        (baseActivity as BGGActivity).setThingBGGSelected(thingBGGModel)
    }

    override fun onWhatsappThingClick(thingBGGModel: ThingBGGModel) {
        WhatsappUtils.sendWhatsapp(baseActivity, thingBGGModel.bestName())
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.ivCheckTotalBoardGames -> {
                if (listThingsBGGViewModel.typeShowConfigSelectedListMutableLiveData.value != TYPE_THING_UNKNOW) {
                    listThingsBGGViewModel.showList(TYPE_THING_UNKNOW)
                    binding?.mlListThings?.transitionToStart()
                    binding?.rvListThingsBGG?.smoothScrollToPosition(0)
                }
            }
            R.id.ivCheckTotalBoardGamesBasic -> {
                if (listThingsBGGViewModel.typeShowConfigSelectedListMutableLiveData.value != TYPE_THING_BOARDGAME) {
                    listThingsBGGViewModel.showList(TYPE_THING_BOARDGAME)
                    binding?.mlListThings?.transitionToStart()
                    binding?.rvListThingsBGG?.smoothScrollToPosition(0)
                }
            }
            R.id.ivCheckTotalExpansion -> {
                if (listThingsBGGViewModel.typeShowConfigSelectedListMutableLiveData.value != TYPE_THING_EXPANSION) {
                    listThingsBGGViewModel.showList(TYPE_THING_EXPANSION)
                    binding?.mlListThings?.transitionToStart()
                    binding?.rvListThingsBGG?.smoothScrollToPosition(0)
                }
            }
            R.id.btLaunchIntent -> {
                val intent = Intent(baseActivity, CalculatorDetailActivity::class.java)
                intent.putExtra(IntentKeys.INTENT_KEY_CALCULATOR_DETAIL, "Todo ha salido perfecto.")
                startActivity(intent)
            }
        }
    }
}
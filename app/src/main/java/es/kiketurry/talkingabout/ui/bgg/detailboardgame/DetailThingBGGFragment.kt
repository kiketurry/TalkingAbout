package es.kiketurry.talkingabout.ui.bgg.detailboardgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel
import es.kiketurry.talkingabout.databinding.FragmentBggDetailBoardGameBinding
import es.kiketurry.talkingabout.extensions.visible
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseFragment
import es.kiketurry.talkingabout.ui.bgg.BGGActivity
import es.kiketurry.talkingabout.utils.WhatsappUtils

class DetailThingBGGFragment : BaseFragment<FragmentBggDetailBoardGameBinding>() {
    override val TAG: String? get() = DetailThingBGGFragment::class.qualifiedName

    lateinit var detailThingBGGViewModel: DetailThingBGGViewModel

    var nameThing: String = ""

    override fun inflateBinding() {
        binding = FragmentBggDetailBoardGameBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        detailThingBGGViewModel =
            ViewModelProvider(
                this,
                InjectionSingleton.provideViewModelFactory(requireContext())
            ).get(DetailThingBGGViewModel::class.java)
    }

    override fun observeViewModel() {
        detailThingBGGViewModel.errorMutableLiveData.observe(viewLifecycleOwner, this::showError)
        detailThingBGGViewModel.loadingMutableLiveData.observe(viewLifecycleOwner, this::showLoading)

        (baseActivity as BGGActivity).bggViewModel.thingBGGSelectedMutableLiveData.observe(viewLifecycleOwner, this::populateDataThingBGG)
    }

    private fun populateDataThingBGG(thingBGGModel: ThingBGGModel) {
        binding?.tvName?.text = thingBGGModel.bestName()
        binding?.ivThingBGG?.context?.let {
            Glide.with(it)
                .load(thingBGGModel.image)
                .placeholder(R.drawable.loading_image_bgg)
                .apply(RequestOptions().error(R.drawable.loading_image_bgg))
                .into(binding?.ivThingBGG!!)
        }
        binding?.tvType?.text = thingBGGModel.getStringType()
        binding?.tvRank?.text = thingBGGModel.rank
        binding?.tvWeight?.text = "${thingBGGModel.weight} / 5.0"
        binding?.tvYear?.text = thingBGGModel.yearPublished
        binding?.tvTime?.text = thingBGGModel.time
        binding?.tvPlayers?.text =
            "${thingBGGModel.playersNumber} jugadores, la gente recomienda: ${thingBGGModel.playersRecommendedCommunity}"
        binding?.tvAge?.text = "Edad mínima ${thingBGGModel.ageMin}, la gente recomienda: ${thingBGGModel.ageMinRecommendedCommunity}"
        binding?.tvLanguageDependence?.text = "Dependencia idioma: ${thingBGGModel.getStringLanguageDependence()}"
        binding?.tvDescription?.text = thingBGGModel.description
        binding?.tvOtherNames?.text = thingBGGModel.getStringListNames()
        binding?.ivWhatsapp?.visible()
    }

    override fun configureToolbar() {
        baseActivity.showTitleToolbar(getString(R.string.toolbarDetailThingBGG))
        baseActivity.showBackToolbar(true)
        baseActivity.showCloseToolbar(false)
    }

    override fun createViewAfterInflateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        binding?.ivWhatsapp?.setOnClickListener {
            WhatsappUtils.sendWhatsapp(baseActivity, nameThing)
        }
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {
    }
}
package es.kiketurry.talkingabout.ui.cats.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.databinding.FragmentDetailCatMotionLayoutBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseFragment
import es.kiketurry.talkingabout.ui.cats.CatsActivity
import es.kiketurry.talkingabout.ui.cats.detail.viewmodel.DetailCatViewModel

class DetailCatFragmentMotionLayout : BaseFragment<FragmentDetailCatMotionLayoutBinding>(), MotionLayout.TransitionListener {
    override val TAG: String? get() = DetailCatFragmentMotionLayout::class.qualifiedName

    lateinit var detailCatViewModel: DetailCatViewModel

    private var lastProgress = 0f
    private var fragment: Fragment? = null
    private var last: Float = 0f

    override fun setupViewModel() {
        detailCatViewModel = ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(context!!)).get(DetailCatViewModel::class.java)
    }

    override fun observeViewModel() {
        detailCatViewModel.errorMutableLiveData.observe(viewLifecycleOwner, this::showError)
        detailCatViewModel.loadingMutableLiveData.observe(viewLifecycleOwner, this::showLoading)

        (baseActivity as CatsActivity).catsViewModel.breedCatSelectedMutableLiveData.observe(viewLifecycleOwner, Observer { breedModel ->
            Log.d(TAG, "l> breedCatSelectedMutableLiveData posted")
            binding?.tvBreed?.text = breedModel.name
            binding?.tvDescription?.text = breedModel.description
            binding?.tvAdaptability?.text = breedModel.adaptability.toString()
            binding?.tvIntelligence?.text = breedModel.intelligence.toString()
            binding?.tvAffectionLevel?.text = breedModel.affectionLevel.toString()
            binding?.tvChildFriendly?.text = breedModel.childFriendly.toString()
            binding?.tvDogFriendly?.text = breedModel.dogFriendly.toString()
        })
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) = Unit

    override fun inflateBinding() {
        binding = FragmentDetailCatMotionLayoutBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        binding?.mlContainer?.setTransitionListener(this)
        if (savedInstanceState == null) {
            fragment = DetailCatFragmentMotionLayoutMiniatureCamera().also {
                childFragmentManager.beginTransaction()
                    .replace(R.id.tflContainerPhotos, it)
                    .commitNow()
            }
        }
    }

    override fun configureToolbar() {
        baseActivity.showBackToolbar(true)
        baseActivity.showTitleToolbar(R.string.toolbar_title_fragment_detail_cat)
        baseActivity.showCloseToolbar(true)
    }

    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
        if (p3 - lastProgress > 0) {
            // from start to end
            val atEnd = Math.abs(p3 - 1f) < 0.1f
            if (atEnd && fragment is DetailCatFragmentMotionLayoutMiniatureCamera) {
                val transaction = childFragmentManager.beginTransaction()
                transaction
                    .setCustomAnimations(R.animator.show, 0)
                fragment = DetailCatFragmentMotionLayoutPhotos().also {
                    transaction
                        .setCustomAnimations(R.animator.show, 0)
                        .replace(R.id.tflContainerPhotos, it)
                        .commitNow()
                }
            }
        } else {
            // from end to start
            val atStart = p3 < 0.9f
            if (atStart && fragment is DetailCatFragmentMotionLayoutPhotos) {
                val transaction = childFragmentManager.beginTransaction()
                transaction
                    .setCustomAnimations(0, R.animator.hide)
                fragment = DetailCatFragmentMotionLayoutMiniatureCamera().also {
                    transaction
                        .replace(R.id.tflContainerPhotos, it)
                        .commitNow()
                }
            }
        }
        lastProgress = p3
    }

    override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) = Unit

    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) = Unit

    override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) = Unit

}
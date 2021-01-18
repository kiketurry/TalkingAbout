package es.kiketurry.talkingabout.ui.cats.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.kiketurry.talkingabout.databinding.FragmentDetailCatMotionLayoutMiniatureCameraBinding
import es.kiketurry.talkingabout.ui.base.BaseFragment

class DetailCatFragmentMotionLayoutMiniatureCamera : BaseFragment<FragmentDetailCatMotionLayoutMiniatureCameraBinding>() {
    override val TAG: String? get() = DetailCatFragmentMotionLayoutMiniatureCamera::class.qualifiedName

    override fun setupViewModel() = Unit

    override fun observeViewModel() = Unit

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) = Unit

    override fun inflateBinding() {
        binding = FragmentDetailCatMotionLayoutMiniatureCameraBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = Unit

    override fun configureToolbar() = Unit
}
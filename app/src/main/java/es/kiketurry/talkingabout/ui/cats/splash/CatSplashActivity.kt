package es.kiketurry.talkingabout.ui.cats.splash

import android.content.Intent
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
import es.kiketurry.talkingabout.databinding.ActivitySplashBinding
import es.kiketurry.talkingabout.ui.base.BaseActivity
import es.kiketurry.talkingabout.ui.cats.CatsActivity


class CatSplashActivity : BaseActivity<ActivitySplashBinding>() {
    override val TAG: String? get() = CatSplashActivity::class.qualifiedName

    override fun inflateBinding() {
        binding = ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() = Unit

    override fun observeViewModel() = Unit


    override fun createAfterInflateBindingSetupObserverViewModel(savedInstanceState: Bundle?) {
        binding.motionLayoutBackgroundCat.setTransitionListener(object : TransitionListener {
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) = Unit

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) = Unit

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                goToDistributiveActivity()
            }

            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) = Unit
        })
    }

    private fun goToDistributiveActivity() {
        startActivity(Intent(this, CatsActivity::class.java))
        finish()
    }

    override fun configureToolbar() = Unit
}
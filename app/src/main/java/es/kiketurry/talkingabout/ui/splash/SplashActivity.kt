package es.kiketurry.talkingabout.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
import es.kiketurry.talkingabout.databinding.ActivitySplashBinding
import es.kiketurry.talkingabout.ui.MainActivity
import es.kiketurry.talkingabout.ui.base.BaseActivity


class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override val TAG: String? get() = SplashActivity::class.qualifiedName

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
                goToMainActivity()
            }

            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) = Unit
        })
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
//        startActivity(Intent(this, CalculatorActivity::class.java))
        finish()
    }

    override fun configureToolbar() = Unit
}
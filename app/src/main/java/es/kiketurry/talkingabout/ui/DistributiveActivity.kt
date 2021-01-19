package es.kiketurry.talkingabout.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.databinding.ActivityDistributiveBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseActivity
import es.kiketurry.talkingabout.ui.bgg.BGGActivity
import es.kiketurry.talkingabout.ui.calculator.CalculatorActivity
import es.kiketurry.talkingabout.ui.cats.CatsActivity

class DistributiveActivity : BaseActivity<ActivityDistributiveBinding>() {
    override val TAG: String? get() = DistributiveActivity::class.qualifiedName

    lateinit var distributiveViewModel: DistributiveViewModel

    override fun inflateBinding() {
        binding = ActivityDistributiveBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        distributiveViewModel =
            ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(this)).get(DistributiveViewModel::class.java)
    }

    override fun observeViewModel() = Unit

    override fun createAfterInflateBindingSetupObserverViewModel(savedInstanceState: Bundle?) {
        binding.btCats.setOnClickListener(this)
        binding.btCalculator.setOnClickListener(this)
        binding.btBGG.setOnClickListener(this)
        binding.btBGG.performClick()
    }

    override fun configureToolbar() {
        showTitleToolbar(getString(R.string.toolbarDistributive))
        showBackToolbar(false)
        showCloseToolbar(true)
    }

    override fun onClick(view: View?) {
        super.onClick(view)
        when (view?.id) {
            R.id.btCats -> {
                startActivity(Intent(this, CatsActivity::class.java))
            }
            R.id.btCalculator -> {
                startActivity(Intent(this, CalculatorActivity::class.java))
            }
            R.id.btBGG -> {
                startActivity(Intent(this, BGGActivity::class.java))
            }
        }
    }
}
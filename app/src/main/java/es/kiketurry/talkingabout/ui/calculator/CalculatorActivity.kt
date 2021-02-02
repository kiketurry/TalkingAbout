package es.kiketurry.talkingabout.ui.calculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.data.constants.IntentKeys.Companion.INTENT_KEY_CALCULATOR_DETAIL
import es.kiketurry.talkingabout.databinding.ActivityCalculatorBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseActivity
import es.kiketurry.talkingabout.ui.calculator.detail.CalculatorDetailActivity

class CalculatorActivity : BaseActivity<ActivityCalculatorBinding>() {
    override val TAG: String? get() = CalculatorActivity::class.qualifiedName

    lateinit var calculatorViewModel: CalculatorViewModel

    override fun inflateBinding() {
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        calculatorViewModel =
            ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(this)).get(
                CalculatorViewModel::class.java
            )
    }

    override fun observeViewModel() {
        calculatorViewModel.resultMutableLiveData.observe(this, this::putResult)
    }

    private fun putResult(result: String) {
        binding.tvResult.text = result
    }

    override fun createAfterInflateBindingSetupObserverViewModel(savedInstanceState: Bundle?) {
        binding.rbAdd.setOnClickListener(this)
        binding.rbSubtract.setOnClickListener(this)
        binding.rbMultiply.setOnClickListener(this)
        binding.rbDivide.setOnClickListener(this)
        binding.btGoToDetail.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        super.onClick(view)
        hideKeyboard()
        when (view?.id) {
            binding.rbAdd.id -> {
                uncheckAllRadioButtons()
                binding.rbAdd.isChecked = true
                calculatorViewModel.add(binding.etOperatorOne.text.toString(), binding.etOperatorTwo.text.toString())
            }
            binding.rbSubtract.id -> {
                uncheckAllRadioButtons()
                binding.rbSubtract.isChecked = true
                calculatorViewModel.subtract(binding.etOperatorOne.text.toString(), binding.etOperatorTwo.text.toString())
            }
            binding.rbMultiply.id -> {
                uncheckAllRadioButtons()
                binding.rbMultiply.isChecked = true
                calculatorViewModel.multiply(binding.etOperatorOne.text.toString(), binding.etOperatorTwo.text.toString())
            }
            binding.rbDivide.id -> {
                uncheckAllRadioButtons()
                binding.rbDivide.isChecked = true
                calculatorViewModel.divide(binding.etOperatorOne.text.toString(), binding.etOperatorTwo.text.toString())
            }
            binding.btGoToDetail.id -> {
                intent = Intent(this, CalculatorDetailActivity::class.java)
                intent.putExtra(INTENT_KEY_CALCULATOR_DETAIL, "Todo ha salido perfecto.")
                startActivity(intent)
            }
        }
    }

    private fun uncheckAllRadioButtons() {
        binding.rbAdd.isChecked = false
        binding.rbSubtract.isChecked = false
        binding.rbMultiply.isChecked = false
        binding.rbDivide.isChecked = false
    }

    override fun configureToolbar() {
        showTitleToolbar(R.string.titleToolbarCalculator)
        showBackToolbar(false)
        showCloseToolbar(true)
    }
}
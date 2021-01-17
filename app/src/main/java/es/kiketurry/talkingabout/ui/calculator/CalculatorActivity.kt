package es.kiketurry.talkingabout.ui.calculator

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.databinding.ActivityCalculatorBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseActivity

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
        binding.btSendWhatsapp.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        super.onClick(view)
        hideKeyboard()
        when (view?.id) {
            binding.btSendWhatsapp.id -> {
                sendWhatsapp()
            }
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
        }
    }

    private fun sendWhatsapp() {
        val whatsappInstalled: Boolean = appInstalled("com.whatsapp")
        val mobileNumber = 34689103476
        val messageWhatsapp = "Mensaje de prueba estoy currando"

        if (whatsappInstalled) {
            intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$mobileNumber&text=$messageWhatsapp")
            startActivity(intent)
        } else {
            Toast.makeText(this, "Whatsapp not installed on your device", Toast.LENGTH_SHORT).show();
        }
    }

    private fun appInstalled(packageString: String): Boolean {
        var appInstalled: Boolean
        try {
            packageManager.getPackageInfo(packageString, PackageManager.GET_ACTIVITIES)
            appInstalled = true
        } catch (exception: Exception) {
            Log.w(TAG, "l> Problemas revisando si la app esta instalada: $exception.message")
            appInstalled = false
        }
        return appInstalled
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
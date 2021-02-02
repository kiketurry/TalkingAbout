package es.kiketurry.talkingabout.ui.calculator.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.databinding.ActivityCalculatorDetailBinding
import es.kiketurry.talkingabout.extensions.toast
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseActivity

class CalculatorDetailActivity : BaseActivity<ActivityCalculatorDetailBinding>() {
    override val TAG: String? get() = CalculatorDetailActivity::class.qualifiedName

    lateinit var calculatorDetailViewModel: CalculatorDetailViewModel

    override fun inflateBinding() {
        binding = ActivityCalculatorDetailBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        calculatorDetailViewModel =
            ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(this)).get(
                CalculatorDetailViewModel::class.java
            )
    }

    override fun observeViewModel() {
    }

    override fun createAfterInflateBindingSetupObserverViewModel(savedInstanceState: Bundle?) {

        binding.spDetail.onItemSelectedListener = (object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0) {
                    Log.i(TAG, "l> le ponemos de texto: -${parent?.selectedItem.toString()}-")
                    binding.tvDetail.text = parent?.selectedItem.toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        })

        val names: ArrayList<String> = ArrayList()
        names.add("Alberto");
        names.add("Manuel");
        names.add("Laura");
        names.add("Monica");
        names.add("Pablo");

        val dataAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, names)

        binding.spDetail.adapter = dataAdapter


        binding.btLogin.setOnClickListener {
            toast("Testeando un toast")
            if (binding.etUser.length() < 4) {
                binding.etUser.error = "Tenemos un error en el username"
                return@setOnClickListener
            }
            if (binding.etPassword.length() < 4) {
                binding.etPassword.error = "Tenemos un error en la password"
                return@setOnClickListener
            }
        }

    }

    override fun configureToolbar() {
        showTitleToolbar(R.string.titleToolbarCalculator)
        showBackToolbar(false)
        showCloseToolbar(false)
    }
}
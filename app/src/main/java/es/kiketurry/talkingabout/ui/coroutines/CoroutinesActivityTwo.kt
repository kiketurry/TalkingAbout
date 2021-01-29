package es.kiketurry.talkingabout.ui.coroutines

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.databinding.ActivityCoroutinesBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseActivity

class CoroutinesActivityTwo : BaseActivity<ActivityCoroutinesBinding>() {
    override val TAG: String? get() = CoroutinesActivityTwo::class.qualifiedName

    lateinit var coroutinesViewModel: CoroutinesViewModel

    override fun inflateBinding() {
        binding = ActivityCoroutinesBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        coroutinesViewModel = ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(this)).get(CoroutinesViewModel::class.java)
    }

    override fun observeViewModel() {
        coroutinesViewModel.resultMutableLiveData.observe(this, { result ->
            Log.i(TAG, "l> result: $result")
        })
    }

    override fun createAfterInflateBindingSetupObserverViewModel(savedInstanceState: Bundle?) {
        coroutinesViewModel.comeOn()
    }

    override fun configureToolbar() {
    }
}
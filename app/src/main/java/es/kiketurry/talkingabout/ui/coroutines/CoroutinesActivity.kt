package es.kiketurry.talkingabout.ui.coroutines

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import es.kiketurry.talkingabout.databinding.ActivityCoroutinesBinding
import es.kiketurry.talkingabout.extensions.toast
import es.kiketurry.talkingabout.ui.base.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutinesActivity : BaseActivity<ActivityCoroutinesBinding>() {
    override val TAG: String? get() = CoroutinesActivity::class.qualifiedName

    override fun inflateBinding() {
        binding = ActivityCoroutinesBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun observeViewModel() {
    }

    override fun createAfterInflateBindingSetupObserverViewModel(savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            Log.i(TAG, "l> 1 Init launch modo secuencia en paralelo")
            val operationOne = withContext(Dispatchers.IO) {
                operationOne()
            }
            val operationTwo = withContext(Dispatchers.IO) {
                operationTwo()
            }

            Log.i(TAG, "l> 1 Jeje operationOne: $operationOne jojo operationTwo: $operationTwo")
            toast("Jeje operationOne: $operationOne jojo operationTwo: $operationTwo")
        }

        lifecycleScope.launch {
            Log.i(TAG, "l> 2 Init launch con await modo paralelo")
            val operationOne = async(Dispatchers.IO) {
                operationOne()
            }
            val operationTwo = async(Dispatchers.IO) {
                operationTwo()
            }

            //La suspension vemos que se genera en el punto del await.
            Log.i(TAG, "l> 2 Jeje operationOne: ${operationOne.await()} jojo operationTwo: ${operationTwo.await()}")
            toast("Jeje operationOne: ${operationOne.await()} jojo operationTwo: ${operationTwo.await()}")
        }
    }

    override fun configureToolbar() {
    }


    private fun operationOne(): Boolean {
        Thread.sleep(5000)
        return true
    }

    private fun operationTwo(): Boolean {
        Thread.sleep(3000)
        return false
    }
}
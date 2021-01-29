package es.kiketurry.talkingabout.ui.coroutines

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import es.kiketurry.talkingabout.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutinesViewModel(ioDispatcher: CoroutineDispatcher = Dispatchers.IO, application: Application) : BaseViewModel(ioDispatcher, application) {

    override val TAG: String? get() = CoroutinesViewModel::class.qualifiedName

    var resultMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun comeOn() {
        viewModelScope.launch {
            resultMutableLiveData.value = withContext(ioDispatcher) { operationOne() }
        }
    }

    private fun operationOne(): Boolean {
        Thread.sleep(5000)
        return true
    }
}
package es.kiketurry.talkingabout.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModel(val ioDispatcher: CoroutineDispatcher = Dispatchers.IO, application: Application) : AndroidViewModel(application) {
    abstract val TAG: String?

    var loadingMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()

    var errorMutableLiveData: MutableLiveData<ErrorModel> = MutableLiveData()
}
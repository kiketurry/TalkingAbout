package es.kiketurry.talkingabout.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    abstract val TAG: String?

    var loadingMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()

    var errorMutableLiveData: MutableLiveData<ErrorModel> = MutableLiveData()

}
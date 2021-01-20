package es.kiketurry.talkingabout.ui.bgg

import android.app.Application
import androidx.lifecycle.MutableLiveData
import es.kiketurry.talkingabout.ui.base.BaseViewModel

class BGGViewModel(application: Application) : BaseViewModel(application) {
    override val TAG: String? get() = BGGViewModel::class.qualifiedName

    var userBGGSelectedMutableLiveData: MutableLiveData<String> = MutableLiveData()

    fun setUserBGGSelected(userBGG: String) {
        userBGGSelectedMutableLiveData.postValue(userBGG)
    }

}
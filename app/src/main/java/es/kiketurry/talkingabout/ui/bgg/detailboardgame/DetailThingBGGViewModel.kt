package es.kiketurry.talkingabout.ui.bgg.detailboardgame

import android.app.Application
import es.kiketurry.talkingabout.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DetailThingBGGViewModel(ioDispatcher: CoroutineDispatcher = Dispatchers.IO, application: Application) : BaseViewModel(ioDispatcher, application) {

    override val TAG: String? get() = DetailThingBGGViewModel::class.qualifiedName

}
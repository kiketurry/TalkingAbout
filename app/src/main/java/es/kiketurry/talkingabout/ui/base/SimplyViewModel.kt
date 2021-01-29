package es.kiketurry.talkingabout.ui.base

import android.app.Application
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class SimplyViewModel(ioDispatcher: CoroutineDispatcher = Dispatchers.IO, application: Application) : BaseViewModel(ioDispatcher, application) {
    override val TAG: String? get() = SimplyViewModel::class.qualifiedName
}
package es.kiketurry.talkingabout.ui.base

import android.app.Application

class SimplyViewModel(application: Application) : BaseViewModel(application) {
    override val TAG: String? get() = SimplyViewModel::class.qualifiedName
}
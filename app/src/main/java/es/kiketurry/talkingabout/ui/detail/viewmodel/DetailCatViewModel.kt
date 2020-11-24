package es.kiketurry.talkingabout.ui.detail.viewmodel

import android.app.Application
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.ui.base.BaseViewModel

class DetailCatViewModel(application: Application, dataProvider: DataProvider) : BaseViewModel(application) {
    override val TAG: String? get() = DetailCatViewModel::class.qualifiedName
}
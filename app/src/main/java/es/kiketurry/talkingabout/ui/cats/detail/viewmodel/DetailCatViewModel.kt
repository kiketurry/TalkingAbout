package es.kiketurry.talkingabout.ui.cats.detail.viewmodel

import android.app.Application
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DetailCatViewModel(ioDispatcher: CoroutineDispatcher = Dispatchers.IO, application: Application, val dataProvider: DataProvider) :
    BaseViewModel(ioDispatcher, application) {
    override val TAG: String? get() = DetailCatViewModel::class.qualifiedName
}
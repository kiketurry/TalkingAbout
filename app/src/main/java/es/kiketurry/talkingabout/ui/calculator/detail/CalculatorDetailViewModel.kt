package es.kiketurry.talkingabout.ui.calculator.detail

import android.app.Application
import es.kiketurry.talkingabout.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CalculatorDetailViewModel(ioDispatcher: CoroutineDispatcher = Dispatchers.IO, application: Application) : BaseViewModel(ioDispatcher, application) {
    override val TAG: String? get() = CalculatorDetailViewModel::class.qualifiedName
}
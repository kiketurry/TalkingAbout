package es.kiketurry.talkingabout.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.data.repository.DataProviderXML
import es.kiketurry.talkingabout.ui.MainViewModel
import es.kiketurry.talkingabout.ui.calculator.CalculatorViewModel
import es.kiketurry.talkingabout.ui.detail.viewmodel.DetailCatViewModel
import es.kiketurry.talkingabout.ui.list.BreedListViewModel

class ViewModelFactory(var application: Application, var dataProvider: DataProvider, var dataProviderXML: DataProviderXML) :
    ViewModelProvider.Factory {
    companion object {
        var INSTANCE: ViewModelFactory? = null

        @Synchronized
        fun getInstance(application: Application, dataProvider: DataProvider, dataProviderXML: DataProviderXML): ViewModelFactory {
            if (INSTANCE == null) {
                INSTANCE = ViewModelFactory(application, dataProvider, dataProviderXML)
            }
            return INSTANCE!!
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel: ViewModel = when (modelClass.name) {
            MainViewModel::class.qualifiedName -> {
                MainViewModel(application, dataProvider, dataProviderXML)
            }
            BreedListViewModel::class.qualifiedName -> {
                BreedListViewModel(application, dataProvider)
            }
            DetailCatViewModel::class.qualifiedName -> {
                DetailCatViewModel(application, dataProvider)
            }
            CalculatorViewModel::class.qualifiedName -> {
                CalculatorViewModel(application)
            }
            else -> {
                SimplyViewModel(application)
            }
        }

        return viewModel as T
    }
}
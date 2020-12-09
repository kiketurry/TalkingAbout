package es.kiketurry.talkingabout.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.ui.MainViewModel
import es.kiketurry.talkingabout.ui.calculator.CalculatorViewModel
import es.kiketurry.talkingabout.ui.detail.viewmodel.DetailCatViewModel
import es.kiketurry.talkingabout.ui.list.BreedListViewModel

class ViewModelFactory(var application: Application, var dataProvider: DataProvider) : ViewModelProvider.Factory {
    companion object {
        var INSTANCE: ViewModelFactory? = null

        @Synchronized
        fun getInstance(application: Application, dataProvider: DataProvider): ViewModelFactory {
            if (INSTANCE == null) {
                INSTANCE = ViewModelFactory(application, dataProvider)
            }
            return INSTANCE!!
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel: ViewModel = when {
            modelClass.name.equals(MainViewModel::class.qualifiedName) -> {
                MainViewModel(application, dataProvider)
            }
            modelClass.name.equals(BreedListViewModel::class.qualifiedName) -> {
                BreedListViewModel(application, dataProvider)
            }
            modelClass.name.equals(DetailCatViewModel::class.qualifiedName) -> {
                DetailCatViewModel(application, dataProvider)
            }
            modelClass.name.equals(CalculatorViewModel::class.qualifiedName) -> {
                CalculatorViewModel(application)
            }
            else -> {
                SimplyViewModel(application)
            }
        }

        return viewModel as T
    }
}
package es.kiketurry.talkingabout.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.data.repository.DataProviderXML
import es.kiketurry.talkingabout.data.repository.bbdd.BBDDManager
import es.kiketurry.talkingabout.ui.DistributiveViewModel
import es.kiketurry.talkingabout.ui.bgg.BGGViewModel
import es.kiketurry.talkingabout.ui.bgg.adduser.AddUsersBGGViewModel
import es.kiketurry.talkingabout.ui.bgg.listusers.ListUsersBGGViewModel
import es.kiketurry.talkingabout.ui.calculator.CalculatorViewModel
import es.kiketurry.talkingabout.ui.cats.CatsViewModel
import es.kiketurry.talkingabout.ui.cats.detail.viewmodel.DetailCatViewModel
import es.kiketurry.talkingabout.ui.cats.list.BreedListViewModel

class ViewModelFactory(
    var application: Application,
    var bbddManager: BBDDManager,
    var dataProvider: DataProvider,
    var dataProviderXML: DataProviderXML
) :
    ViewModelProvider.Factory {
    companion object {
        var INSTANCE: ViewModelFactory? = null

        @Synchronized
        fun getInstance(
            application: Application,
            bbddManager: BBDDManager,
            dataProvider: DataProvider,
            dataProviderXML: DataProviderXML
        ): ViewModelFactory {
            if (INSTANCE == null) {
                INSTANCE = ViewModelFactory(application, bbddManager, dataProvider, dataProviderXML)
            }
            return INSTANCE!!
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel: ViewModel = when (modelClass.name) {
            DistributiveViewModel::class.qualifiedName -> {
                DistributiveViewModel(application)
            }
            CatsViewModel::class.qualifiedName -> {
                CatsViewModel(application, dataProvider, dataProviderXML)
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
            BGGViewModel::class.qualifiedName -> {
                BGGViewModel(application)
            }
            ListUsersBGGViewModel::class.qualifiedName -> {
                ListUsersBGGViewModel(application, bbddManager)
            }
            AddUsersBGGViewModel::class.qualifiedName -> {
                AddUsersBGGViewModel(application, bbddManager)
            }
            else -> {
                SimplyViewModel(application)
            }
        }

        return viewModel as T
    }
}
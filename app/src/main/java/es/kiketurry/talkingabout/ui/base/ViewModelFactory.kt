package es.kiketurry.talkingabout.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase
import es.kiketurry.talkingabout.ui.DistributiveViewModel
import es.kiketurry.talkingabout.ui.bgg.BGGViewModel
import es.kiketurry.talkingabout.ui.bgg.addmodifyuser.AddModifyUsersBGGViewModel
import es.kiketurry.talkingabout.ui.bgg.detailboardgame.DetailThingBGGViewModel
import es.kiketurry.talkingabout.ui.bgg.listthings.ListThingsBGGViewModel
import es.kiketurry.talkingabout.ui.bgg.listusers.ListUsersBGGViewModel
import es.kiketurry.talkingabout.ui.calculator.CalculatorViewModel
import es.kiketurry.talkingabout.ui.calculator.detail.CalculatorDetailViewModel
import es.kiketurry.talkingabout.ui.cats.CatsViewModel
import es.kiketurry.talkingabout.ui.cats.detail.viewmodel.DetailCatViewModel
import es.kiketurry.talkingabout.ui.cats.list.BreedListViewModel
import es.kiketurry.talkingabout.ui.coroutines.CoroutinesViewModel

class ViewModelFactory(
    val application: Application,
    val appDatabase: AppDatabase,
    val dataProvider: DataProvider,
) :
    ViewModelProvider.Factory {
    companion object {
        var INSTANCE: ViewModelFactory? = null

        @Synchronized
        fun getInstance(
            application: Application,
            appDatabase: AppDatabase,
            dataProvider: DataProvider
        ): ViewModelFactory {
            if (INSTANCE == null) {
                INSTANCE = ViewModelFactory(application, appDatabase, dataProvider)
            }
            return INSTANCE!!
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel: ViewModel = when (modelClass.name) {
            DistributiveViewModel::class.qualifiedName -> {
                DistributiveViewModel(application = application)
            }
            CatsViewModel::class.qualifiedName -> {
                CatsViewModel(application = application, dataProvider = dataProvider)
            }
            BreedListViewModel::class.qualifiedName -> {
                BreedListViewModel(application = application, dataProvider = dataProvider)
            }
            DetailCatViewModel::class.qualifiedName -> {
                DetailCatViewModel(application = application, dataProvider = dataProvider)
            }
            CalculatorViewModel::class.qualifiedName -> {
                CalculatorViewModel(application = application)
            }
            CalculatorDetailViewModel::class.qualifiedName -> {
                CalculatorDetailViewModel(application = application)
            }
            BGGViewModel::class.qualifiedName -> {
                BGGViewModel(application = application, appDatabase = appDatabase, dataProvider = dataProvider)
            }
            ListUsersBGGViewModel::class.qualifiedName -> {
                ListUsersBGGViewModel(application = application, appDatabase = appDatabase)
            }
            AddModifyUsersBGGViewModel::class.qualifiedName -> {
                AddModifyUsersBGGViewModel(application = application, appDatabase = appDatabase)
            }
            ListThingsBGGViewModel::class.qualifiedName -> {
                ListThingsBGGViewModel(application = application, appDatabase = appDatabase, dataProvider = dataProvider)
            }
            DetailThingBGGViewModel::class.qualifiedName -> {
                DetailThingBGGViewModel(application = application)
            }
            CoroutinesViewModel::class.qualifiedName -> {
                CoroutinesViewModel(application = application)
            }
            else -> {
                SimplyViewModel(application = application)
            }
        }

        return viewModel as T
    }
}
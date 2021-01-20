package es.kiketurry.talkingabout.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase
import es.kiketurry.talkingabout.ui.DistributiveViewModel
import es.kiketurry.talkingabout.ui.bgg.BGGViewModel
import es.kiketurry.talkingabout.ui.bgg.addmodifyuser.AddModifyUsersBGGViewModel
import es.kiketurry.talkingabout.ui.bgg.listboardgames.ListBoardGamesBGGViewModel
import es.kiketurry.talkingabout.ui.bgg.listusers.ListUsersBGGViewModel
import es.kiketurry.talkingabout.ui.calculator.CalculatorViewModel
import es.kiketurry.talkingabout.ui.cats.CatsViewModel
import es.kiketurry.talkingabout.ui.cats.detail.viewmodel.DetailCatViewModel
import es.kiketurry.talkingabout.ui.cats.list.BreedListViewModel

class ViewModelFactory(
    var application: Application,
    var appDatabase: AppDatabase,
    var dataProvider: DataProvider,
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
                DistributiveViewModel(application)
            }
            CatsViewModel::class.qualifiedName -> {
                CatsViewModel(application, dataProvider)
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
                ListUsersBGGViewModel(application, appDatabase)
            }
            AddModifyUsersBGGViewModel::class.qualifiedName -> {
                AddModifyUsersBGGViewModel(application, appDatabase)
            }
            ListBoardGamesBGGViewModel::class.qualifiedName -> {
                ListBoardGamesBGGViewModel(application, appDatabase, dataProvider)
            }
            else -> {
                SimplyViewModel(application)
            }
        }

        return viewModel as T
    }
}
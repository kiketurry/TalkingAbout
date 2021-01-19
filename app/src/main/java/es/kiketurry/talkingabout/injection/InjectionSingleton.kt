package es.kiketurry.talkingabout.injection

import android.app.Application
import android.content.Context
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase
import es.kiketurry.talkingabout.data.repository.remote.*
import es.kiketurry.talkingabout.data.repository.sharedpreferences.SharedPreferencesManager
import es.kiketurry.talkingabout.ui.base.ViewModelFactory

class InjectionSingleton {
    companion object {
        private fun provideAppDatabase(context: Context): AppDatabase {
            return AppDatabase.getInstance(context)
        }

        fun provideSharedPreferencesManager(context: Context): SharedPreferencesManager {
            return SharedPreferencesManager.getInstance(context)
        }

        private fun provideApiServicesCats(): ApiServicesCats {
            return RetrofitClientCats.getInstance().getApiServices()
        }

        private fun provideApiServicesBGG(): ApiServicesBGG {
            return RetrofitClientBGG.getInstance().getApiServicesXML()
        }

        private fun provideDataSource(): DataProvider {
            return DataProvider.getInstance(RemoteDataSource.getInstance(provideApiServicesCats(), provideApiServicesBGG()))
        }

        fun provideViewModelFactory(context: Context): ViewModelFactory {
            return ViewModelFactory.getInstance(
                context.applicationContext as Application,
                provideAppDatabase(context),
                provideDataSource()
            )
        }
    }
}
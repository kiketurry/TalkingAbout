package es.kiketurry.talkingabout.injection

import android.app.Application
import android.content.Context
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.data.repository.remote.ApiServices
import es.kiketurry.talkingabout.data.repository.remote.RemoteDataSource
import es.kiketurry.talkingabout.data.repository.remote.RetrofitClient
import es.kiketurry.talkingabout.data.repository.sharedpreferences.SharedPreferencesManager
import es.kiketurry.talkingabout.ui.base.ViewModelFactory

class InjectionSingleton {
    companion object {
        fun provideSharedPreferencesManager(context: Context): SharedPreferencesManager {
            return SharedPreferencesManager.getInstance(context)
        }

        private fun provideApiServices(): ApiServices {
            return RetrofitClient.getInstance().getApiServices()
        }

        fun provideDataSource(context: Context): DataProvider {
            return DataProvider.getInstance(RemoteDataSource.getInstance(provideApiServices()))
        }

        fun provideViewModelFactory(context: Context): ViewModelFactory {
            return ViewModelFactory.getInstance(context.applicationContext as Application, provideDataSource(context))
        }
    }
}
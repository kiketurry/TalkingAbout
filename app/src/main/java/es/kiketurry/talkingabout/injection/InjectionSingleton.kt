package es.kiketurry.talkingabout.injection

import android.app.Application
import android.content.Context
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.data.repository.DataProviderXML
import es.kiketurry.talkingabout.data.repository.bbdd.BBDDManager
import es.kiketurry.talkingabout.data.repository.remote.*
import es.kiketurry.talkingabout.data.repository.sharedpreferences.SharedPreferencesManager
import es.kiketurry.talkingabout.ui.base.ViewModelFactory

class InjectionSingleton {
    companion object {
        fun provideBBDDManager(context: Context): BBDDManager {
            return BBDDManager.getInstance(context)
        }

        fun provideSharedPreferencesManager(context: Context): SharedPreferencesManager {
            return SharedPreferencesManager.getInstance(context)
        }

        private fun provideApiServices(): ApiServices {
            return RetrofitClient.getInstance().getApiServices()
        }

        private fun provideApiServicesXML(): ApiServicesXML {
            return RetrofitClientXML.getInstance().getApiServicesXML()
        }

        fun provideDataSource(context: Context): DataProvider {
            return DataProvider.getInstance(RemoteDataSource.getInstance(provideApiServices()))
        }

        fun provideDataSourceXML(context: Context): DataProviderXML {
            return DataProviderXML.getInstance(RemoteDataSourceXML.getInstance(provideApiServicesXML()))
        }

        fun provideViewModelFactory(context: Context): ViewModelFactory {
            return ViewModelFactory.getInstance(
                context.applicationContext as Application,
                provideDataSource(context),
                provideDataSourceXML(context)
            )
        }
    }
}
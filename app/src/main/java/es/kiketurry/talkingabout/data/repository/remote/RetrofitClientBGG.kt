package es.kiketurry.talkingabout.data.repository.remote

import es.kiketurry.talkingabout.BuildConfig
import es.kiketurry.talkingabout.data.constants.GeneralConstants.Companion.RETROFIT_TIMEOUT_IN_SECOND
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.io.IOException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class RetrofitClientBGG() {
    companion object {
        var INSTANCE: RetrofitClientBGG? = null

        @Synchronized
        fun getInstance(): RetrofitClientBGG {
            if (INSTANCE == null) {
                INSTANCE = RetrofitClientBGG()
            }
            return INSTANCE!!
        }
    }

    private val retrofit: Retrofit
    private val apiServices: ApiServicesBGG

    init {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(RETROFIT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .readTimeout(RETROFIT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .writeTimeout(RETROFIT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)

        httpClient.interceptors().clear()

        httpClient.interceptors().add(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val request = original.newBuilder()
                    .header(BuildConfig.API_KEY_HEADER, BuildConfig.API_KEY_HEADER_VALUE)
                    .method(original.method, original.body)
                    .build()
                return chain.proceed(request)
            }
        })

        if (BuildConfig.DEBUG) {
            // Creamos un interceptor y le indicamos el log level a usar
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(logging)
        }

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_BGG)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(httpClient.build())
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
        apiServices = retrofit.create(ApiServicesBGG::class.java)
    }

    fun getApiServicesXML(): ApiServicesBGG {
        return apiServices
    }
}
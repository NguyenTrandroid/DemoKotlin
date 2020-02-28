package nguyentrandroid.a.hhll.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import nguyentrandroid.a.hhll.data.services.NotifyService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object API {

    private val gson: Gson = GsonBuilder()
        .disableHtmlEscaping()
        .create()

    private var client: Retrofit = makeClient("https://es-api.hahalolo.com/")

    private fun makeHttpClient(): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient().newBuilder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .addInterceptor(loggingInterceptor).build()

    }


    private fun makeClient(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(makeHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }


     lateinit var notifyService: NotifyService

    private fun createService() {
        notifyService = client.create(NotifyService::class.java) as NotifyService
    }
}
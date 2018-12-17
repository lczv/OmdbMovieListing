package br.com.desafio.mobile.desafiomobile.data.datasource

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiInstance {
    private var OMDB_API: OmdbApi? = null
    private var CONNECTION_TIMEOUT = 5L

    fun create(context: Context, baseUrl: String) {

        val okHttpClient = OkHttpClient
                .Builder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(NetworkInterceptor(context))
                .build()

        OMDB_API = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OmdbApi::class.java)
    }

    fun getApi() = OMDB_API
}
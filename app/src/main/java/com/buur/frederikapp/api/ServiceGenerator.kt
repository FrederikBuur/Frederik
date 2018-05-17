package com.buur.frederikapp.api

import com.buur.frederikapp.api.interfaces.IChampions
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {

    companion object {
        const val API_KEY = "RGAPI-9cc24396-60b9-4605-b75f-138dbb2438df"
        const val BASE_URL = "https://euw1.api.riotgames.com"
    }

    private var retrofit: Retrofit? = null

    private fun getRetrofit(): Retrofit {
        val log = HttpLoggingInterceptor()
        log.level = HttpLoggingInterceptor.Level.BODY
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(OkHttpClient.Builder().addInterceptor(log).build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
        return  retrofit!!
    }

    fun createChampionAPI() : IChampions {
        return getRetrofit().create(IChampions::class.java)
    }

}
package com.buur.frederikapp.api

import com.buur.frederikapp.api.interfaces.IStaticData
import com.buur.frederikapp.api.interfaces.ISummoner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {

    companion object {
        const val API_KEY = "RGAPI-ba4a62c1-2695-4650-833a-5f23eb37a7c9"
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

    fun createStaticDataAPI() : IStaticData {
        return getRetrofit().create(IStaticData::class.java)
    }

    fun createSummonerAPI() : ISummoner {
        return getRetrofit().create(ISummoner::class.java)
    }

}
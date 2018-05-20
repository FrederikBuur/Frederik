package com.buur.frederikapp.controllers

import android.util.Log
import com.buur.frederikapp.api.ErrorHandler
import com.buur.frederikapp.api.ServiceGenerator
import com.buur.frederikapp.api.ServiceGenerator.Companion.API_KEY
import com.buur.frederikapp.api.interfaces.ISummoner
import com.buur.frederikapp.modelsapi.SummonerResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SummonerController {

    private val TAG = "SummonerController"
    private var summonerClient: ISummoner? = null

    private fun getChampionClient(): ISummoner {
        if (summonerClient == null) {
            summonerClient = ServiceGenerator().createSummonerAPI()
        }
        return summonerClient!!
    }

    fun fetchSummoner(name: String) : Observable<SummonerResponse> {

        return getChampionClient().getSummoner(name, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {  summonerResponse ->
                    SessionController.getInstance().summoner = summonerResponse
                }
                .doOnError {  error ->
                    Log.e(TAG, ErrorHandler.handleError(error))
                }
                

    }

}
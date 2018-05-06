package com.buur.frederikapp.fragments.champions

import android.util.Log
import com.buur.frederikapp.api.ServiceGenerator
import com.buur.frederikapp.api.ServiceGenerator.Companion.API_KEY
import com.buur.frederikapp.api.interfaces.IChampions
import com.buur.frederikapp.modelsapi.ChampionListResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class ChampionsController {

    private val TAG = "ChampionsController"
    private var championClient: IChampions? = null

    private fun getChampionClient(): IChampions {
        if (championClient == null) {
            championClient = ServiceGenerator().createChampionAPI()
        }
        return championClient!!
    }

    fun fetchChampionList() : Observable<ChampionListResponse> {

        return getChampionClient().getChampionList(API_KEY, "image", true)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { championList ->
                    Log.d(TAG, championList.data.toString())
                }
                .doOnError {
                    Log.e(TAG, "getChampionList ERROR: \n ${it.message}")
                }

    }
}
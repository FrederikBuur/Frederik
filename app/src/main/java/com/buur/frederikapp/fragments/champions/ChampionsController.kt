package com.buur.frederikapp.fragments.champions

import com.buur.frederikapp.api.ServiceGenerator
import com.buur.frederikapp.api.ServiceGenerator.Companion.API_KEY
import com.buur.frederikapp.api.interfaces.IStaticData
import com.buur.frederikapp.modelsapi.ChampionListResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm

class ChampionsController {

    private val TAG = "ChampionsController"
    private var statickDataClient: IStaticData? = null

    private fun getStaticDataClient(): IStaticData {
        if (statickDataClient == null) {
            statickDataClient = ServiceGenerator().createChampionAPI()
        }
        return statickDataClient!!
    }

    fun fetchChampionList(): Observable<ChampionListResponse> {

        return getStaticDataClient().getChampionList(API_KEY, "image", true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { championList ->
                    // save into realm
                    Realm.getDefaultInstance().use { realm ->
                        realm.executeTransaction { innerRealm ->
                            innerRealm.copyToRealmOrUpdate(championList.data.values)
                        }
                    }
                }
    }
}
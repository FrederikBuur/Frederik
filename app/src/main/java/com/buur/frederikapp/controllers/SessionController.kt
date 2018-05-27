package com.buur.frederikapp.controllers

import com.buur.frederikapp.api.ErrorHandler
import com.buur.frederikapp.api.ServiceGenerator
import com.buur.frederikapp.api.ServiceGenerator.Companion.API_KEY
import com.buur.frederikapp.api.interfaces.IStaticData
import com.buur.frederikapp.models.SummonerSearchResult
import com.buur.frederikapp.models.Version
import com.buur.frederikapp.modelsapi.SummonerResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm

class SessionController {

    internal var summonerSearchResult: SummonerSearchResult? = null

    private var staticDataClient: IStaticData? = null

    private fun getStaticDataClient(): IStaticData {
        if (staticDataClient == null) {
            staticDataClient = ServiceGenerator().createChampionAPI()
        }
        return staticDataClient!!
    }

    fun getVersions() : Observable<ArrayList<String>> {
        return getStaticDataClient().getVersions(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { versionsResponse ->
                    Realm.getDefaultInstance().use { realm ->
                        realm.executeTransaction {
                            val realmVersion = it.createObject(Version::class.java, 1)
                            realmVersion.version = versionsResponse.firstOrNull()
                            it.copyToRealmOrUpdate(realmVersion)
                        }
                    }
                }
                .doOnError {  error ->
                    ErrorHandler.handleError(error)
                }
    }

    companion object {
        private var instanceCompanion: SessionController? = null

        fun getInstance(): SessionController {
            if (instanceCompanion == null) {
                instanceCompanion = SessionController()
            }
            return instanceCompanion as SessionController
        }
    }
}
package com.buur.frederikapp.controllers

import android.util.Log
import com.buur.frederikapp.api.ErrorHandler
import com.buur.frederikapp.api.ServiceGenerator
import com.buur.frederikapp.api.ServiceGenerator.Companion.API_KEY
import com.buur.frederikapp.api.interfaces.IStaticData
import com.buur.frederikapp.models.Version
import com.buur.frederikapp.modelsapi.staticdata.ChampionListResponse
import com.buur.frederikapp.modelsapi.staticdata.ItemListResponse
import com.buur.frederikapp.modelsapi.staticdata.SummonerSpellsListResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm

class StaticDataController {

    private val TAG = "StaticDataController"
    private var statickDataClient: IStaticData? = null

    private fun getStaticDataClient(): IStaticData {
        if (statickDataClient == null) {
            statickDataClient = ServiceGenerator().createStaticDataAPI()
        }
        return statickDataClient!!
    }

    fun fetchChampions(): Observable<ChampionListResponse> {

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
                .doOnError { error ->
                    ErrorHandler.handleError(error)
                }
    }

    fun fetchItemData() : Observable<ItemListResponse> {

        return getStaticDataClient().getItems(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { itemList ->
                    Realm.getDefaultInstance().use { realm ->
                        realm.executeTransaction { innerRealm ->
                            innerRealm.copyToRealmOrUpdate(itemList.data.values)
                        }
                    }
                }
                .doOnError { error ->
                    ErrorHandler.handleError(error)
                }

    }

    fun fetchSummonerSpellData() : Observable<SummonerSpellsListResponse> {

        return getStaticDataClient().getSummonerSpells(API_KEY, true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { summonerSpellList ->
                    Realm.getDefaultInstance().use { realm ->
                        realm.executeTransaction { innerRealm ->
                            innerRealm.copyToRealmOrUpdate(summonerSpellList.data.values)
                        }
                    }
                }
                .doOnError {  error ->
                    ErrorHandler.handleError(error)
                }
    }

    fun getVersions() : Observable<ArrayList<String>> {
        return getStaticDataClient().getVersions(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { versionsResponse ->
                    Realm.getDefaultInstance().use { realm ->
                        realm.executeTransaction {
                            Log.d("SessionController", "version: ${versionsResponse.firstOrNull()}")
                            val realmVersion = Version()
                            realmVersion.version = versionsResponse.firstOrNull()
                            it.copyToRealmOrUpdate(realmVersion)
                        }
                    }
                }
                .doOnError {  error ->
                    ErrorHandler.handleError(error)
                }
    }
}
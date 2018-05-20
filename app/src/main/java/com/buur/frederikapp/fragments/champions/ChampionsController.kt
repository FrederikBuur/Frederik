package com.buur.frederikapp.fragments.champions

import android.util.Log
import com.buur.frederikapp.api.ServiceGenerator
import com.buur.frederikapp.api.ServiceGenerator.Companion.API_KEY
import com.buur.frederikapp.api.interfaces.IChampions
import com.buur.frederikapp.controllers.SessionController
import com.buur.frederikapp.models.Champion
import com.buur.frederikapp.models.ChampionImage
import com.buur.frederikapp.models.ChampionList
import com.buur.frederikapp.modelsapi.ChampionListResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList

class ChampionsController {

    private val TAG = "ChampionsController"
    private var championClient: IChampions? = null

    private fun getChampionClient(): IChampions {
        if (championClient == null) {
            championClient = ServiceGenerator().createChampionAPI()
        }
        return championClient!!
    }

    fun fetchChampionList(): Observable<ChampionListResponse> {

        return getChampionClient().getChampionList(API_KEY, "image", true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { championList ->
                    // save into realm
                    saveChampionListIntoRealm(championList)
                }
                .doOnError {
                    Log.d(TAG, "Something went wrong: ${it.message}")
                }
    }

    private fun saveChampionListIntoRealm(championListResponse: ChampionListResponse) {
        val realm: Realm = Realm.getDefaultInstance()

        realm.executeTransaction { innerRealm ->
            val realmChampionList = innerRealm.createObject(ChampionList::class.java, championListResponse.version)

            realmChampionList.type = championListResponse.type

            val championList: RealmList<Champion> = RealmList()

            for (champion in championListResponse.data.values.toList()) {

                val realmChampion = innerRealm.createObject(Champion::class.java, champion.id)
                realmChampion.name = champion.name
                realmChampion.title = champion.title

                val realmImage = innerRealm.createObject(ChampionImage::class.java)
                realmImage.full = champion.image?.full
                realmImage.group = champion.image?.group
                realmImage.sprite = champion.image?.sprite

                realmChampion.image = realmImage

                championList.add(realmChampion)
            }
            realmChampionList.data = championList
            // sync realm with session controller. save realmChampionList in session controller TODO
        }
        realm.close()
    }
}
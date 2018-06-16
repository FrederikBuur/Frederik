package com.buur.frederikapp.models

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SummonerSpell : RealmObject() {

    @PrimaryKey
    var id: Int = 0
    var summonerLevel: Int? = null
    var name: String? = null
    var key: String? = null
    var description: String? = null

    companion object {

        fun getSummonerSpellKey(realm: Realm, id: Int?) : String? {

            if (id == null) return ""

            return realm.where(SummonerSpell::class.java).equalTo("id", id).findFirst()?.key

        }

    }
}
package com.buur.frederikapp.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ChampionList: RealmObject {

    @PrimaryKey
    var version: String? = null
    var type: String? = null
    var data: RealmList<Champion>? = RealmList()

    constructor()

}
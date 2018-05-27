package com.buur.frederikapp.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Champion : RealmObject() {

    @PrimaryKey
    var id: Int = 0
    var name: String? = null
    var title: String? = null
    var image: ChampionImage? = null

}
package com.buur.frederikapp.models

import io.realm.RealmObject

open class ChampionImage: RealmObject {

    var full: String? = null
    var group: String? = null
    var sprite: String? = null

    constructor()

}
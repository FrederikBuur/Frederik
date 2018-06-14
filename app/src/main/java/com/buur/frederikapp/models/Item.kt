package com.buur.frederikapp.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Item : RealmObject() {

    @PrimaryKey
    var id: Int = 0
    var name: String? = null
    var plaintext: String? = null
    var description: String? = null

    companion object {

//        fun getItem(id: Int)

    }

}
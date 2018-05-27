package com.buur.frederikapp.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Version : RealmObject() {

    @PrimaryKey
    var id = 1
    var version: String? = null

}
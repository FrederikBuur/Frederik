package com.buur.frederikapp.models

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Version : RealmObject() {

    @PrimaryKey
    var id = 1
    var version: String? = null

    companion object {

        fun getLocalVersion(realm: Realm) : String? {

            return realm.where(Version::class.java).findFirst()?.version

        }

    }
}
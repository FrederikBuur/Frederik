package com.buur.frederikapp

import android.app.Application
import android.content.Context
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.google.android.gms.ads.MobileAds
import io.fabric.sdk.android.Fabric
import io.realm.Realm
import io.realm.RealmConfiguration

class FredApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // init realm
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)

        // update champions in realm


        // AdMob
        MobileAds.initialize(this, getString(R.string.admob_app_id))

        initFabric(this)
    }

    private fun initFabric(context: Context) {
        val core = CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()
        val kit = Crashlytics.Builder().core(core).build()
        Fabric.with(context, kit)
    }

}
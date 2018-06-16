package com.buur.frederikapp.controllers

import android.app.Application
import android.content.Context
import com.buur.frederikapp.BuildConfig
import com.buur.frederikapp.R
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.facebook.stetho.Stetho
import com.google.android.gms.ads.MobileAds
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.fabric.sdk.android.Fabric
import io.realm.Realm
import io.realm.RealmConfiguration

class FredApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // init realm
        setupRealm()

        // AdMob
        MobileAds.initialize(this, getString(R.string.admob_app_id))

        // setup Stetho - chrome://inspect/#devices
        setupStetho()

        initFabric(this)
    }

    private fun setupStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                            .build())
        }
    }

    private fun setupRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(config)
    }

    private fun initFabric(context: Context) {
        val core = CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()
        val kit = Crashlytics.Builder().core(core).build()
        Fabric.with(context, kit)
    }

}
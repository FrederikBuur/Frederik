package com.buur.frederikapp.controllers

import android.app.Application
import android.content.Context
import android.media.MediaCas
import com.buur.frederikapp.BuildConfig
import com.buur.frederikapp.R
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.google.android.gms.ads.MobileAds
import io.fabric.sdk.android.Fabric
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.Realm
import io.realm.RealmConfiguration

class FredApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // init realm
        setupRealm()

        // update champions in realm

        // AdMob
        MobileAds.initialize(this, getString(R.string.admob_app_id))

        // Check for newest version
        getCurrentVersion()

        initFabric(this)
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

    private fun getCurrentVersion() {
        SessionController.getInstance().getVersions()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, {})
    }

}
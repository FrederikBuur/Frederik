package com.buur.frederikapp.activities.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.buur.frederikapp.BuildConfig
import com.buur.frederikapp.R
import com.buur.frederikapp.activities.FredActivity
import com.buur.frederikapp.fragments.search.SearchFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : FredActivity() {

    var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        navigateToFragment(SearchFragment(), null, false )

        setupAdMob()
    }

    fun startProgress() {

        // fate in loading stuff

        mainLoadingContainer.visibility = View.VISIBLE
        mainLoadingAnimation.visibility = View.VISIBLE
        mainLoadingAnimation.playAnimation()

    }

    fun stopProgress() {

        mainLoadingContainer.visibility = View.INVISIBLE
        mainLoadingAnimation.visibility = View.INVISIBLE
        mainLoadingAnimation.pauseAnimation()
        mainLoadingAnimation.progress = 0.0f

    }

    private fun setupAdMob() {
        val adRequest = AdRequest.Builder()
                .addTestDevice(getString(R.string.my_test_device_id))
                .build()
        adView?.loadAd(adRequest)
    }
}
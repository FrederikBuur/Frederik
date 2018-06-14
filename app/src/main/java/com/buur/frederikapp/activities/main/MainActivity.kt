package com.buur.frederikapp.activities.main

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.buur.frederikapp.BuildConfig
import com.buur.frederikapp.R
import com.buur.frederikapp.activities.FredActivity
import com.buur.frederikapp.api.ErrorHandler
import com.buur.frederikapp.controllers.StaticDataController
import com.buur.frederikapp.fragments.search.SearchFragment
import com.buur.frederikapp.fragments.startup.StartUpFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : FredActivity() {

    var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        navigateToFragment(StartUpFragment(), shouldAddToBackStack =  false )

        setupAdMob()
    }

    fun startProgress() {

        mainLoadingContainer.visibility = View.VISIBLE
        mainLoadingAnimation.playAnimation()

        // fade in loading stuff
        val fadeAnimation = ObjectAnimator.ofFloat(mainLoadingContainer, View.ALPHA, 0.0f, 0.5f)
        fadeAnimation.duration = 225
        fadeAnimation.start()

    }

    fun stopProgress() {

        mainLoadingContainer.visibility = View.GONE
        mainLoadingAnimation.pauseAnimation()
        mainLoadingAnimation.progress = 0.0f

        // fade out loading stuff
        val fadeAnimation = ObjectAnimator.ofFloat(mainLoadingContainer, View.ALPHA, 5.0f, 0.0f)
        fadeAnimation.duration = 225
        fadeAnimation.start()

    }

    private fun setupAdMob() {
        val adRequest = AdRequest.Builder()
                .addTestDevice(getString(R.string.my_test_device_id))
                .build()
        adView?.loadAd(adRequest)
    }
}
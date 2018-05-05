package com.buur.frederikapp.activities.main

import android.os.Bundle
import android.view.WindowManager
import com.buur.frederikapp.R
import com.buur.frederikapp.activities.FredActivity
import com.buur.frederikapp.fragments.search.SearchFragment

class MainActivity : FredActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        navigateToFragment(SearchFragment(), null, false )

    }
}

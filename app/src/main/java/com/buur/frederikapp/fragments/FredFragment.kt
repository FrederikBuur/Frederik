package com.buur.frederikapp.fragments

import android.support.v4.app.Fragment

abstract class FredFragment : Fragment() {

    open fun handlesOnBackPressed(): Boolean {
        return false
    }

}
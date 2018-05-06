package com.buur.frederikapp.fragments

import android.support.v4.app.Fragment
import com.buur.frederikapp.activities.main.MainActivity

abstract class FredFragment : Fragment() {

    protected val mainActivity: MainActivity?
        get() {
            val activity = activity
            return if (activity is MainActivity) {
                getActivity() as MainActivity?
            } else {
                null
            }
        }

    open fun handlesOnBackPressed(): Boolean {
        return false
    }

}
package com.buur.frederikapp.fragments

import com.buur.frederikapp.activities.main.MainActivity
import com.trello.rxlifecycle2.components.support.RxFragment

abstract class FredFragment : RxFragment() {

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
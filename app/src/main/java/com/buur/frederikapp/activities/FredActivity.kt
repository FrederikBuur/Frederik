package com.buur.frederikapp.activities

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.buur.frederikapp.activities.main.MainActivity
import com.buur.frederikapp.fragments.FredFragment
import kotlinx.android.synthetic.main.activity_main.*

abstract class FredActivity : AppCompatActivity(){

    val screenDimensions: DisplayMetrics
        get() {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics
        }

    val currentFragment: Fragment?
        get() {
            val fragments = supportFragmentManager.fragments
            return if (fragments.size > 0) {
                fragments[fragments.size - 1]
            } else {
                null
            }
        }
    var mainActivity: FredActivity? = null
        get() {
            return this as? MainActivity
        }

    fun navigateToFragment(fragment: Fragment, argument: Bundle? = null, shouldAddToBackStack: Boolean? = true) {

        fragment.arguments = argument
        val supFragMan = supportFragmentManager.beginTransaction()

        val fragmentContainer = when (this) {
            is MainActivity -> mainFragmentContainer.id
            else -> {
                null
            }
        }

        fragmentContainer?.let { supFragMan.replace(fragmentContainer, fragment) }

        if (shouldAddToBackStack == true) {
            val fragTag = fragment.tag
            supFragMan.addToBackStack(fragTag)
        }
        supFragMan.commit()
        hideKeyboard(this)
    }

    fun makeToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard(activity: Activity) {
        if (activity.currentFocus == null) return

        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.currentFocus.windowToken, 0)
    }

    override fun onBackPressed() {

        val currentFrag = currentFragment
        if (currentFrag is FredFragment? && currentFrag?.handlesOnBackPressed() == true) {
            return
        }
        return super.onBackPressed()
    }

}
package com.buur.frederikapp.fragments.summoner.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.buur.frederikapp.modelsapi.match.MatchResponse

abstract class SummonerSuperRowView(context: Context) : RelativeLayout(context) {

    abstract fun setup(match: MatchResponse? = null)

}
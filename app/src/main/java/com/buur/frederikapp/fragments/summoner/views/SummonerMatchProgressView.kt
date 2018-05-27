package com.buur.frederikapp.fragments.summoner.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.buur.frederikapp.R
import com.buur.frederikapp.modelsapi.match.MatchResponse

class SummonerMatchProgressView(context: Context) : SummonerSuperItemView(context) {

    init {
        View.inflate(context, R.layout.view_shared_progress, this)
    }

    override fun setup(match: MatchResponse?) {
    }

}
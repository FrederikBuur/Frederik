package com.buur.frederikapp.fragments.summoner.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.buur.frederikapp.R
import com.buur.frederikapp.controllers.SessionController
import com.buur.frederikapp.modelsapi.SummonerResponse
import com.buur.frederikapp.modelsapi.match.MatchResponse
import com.buur.frederikapp.modelsapi.match.Participant
import kotlinx.android.synthetic.main.view_summoner_matchlist_item_match.view.*

class SummonerMatchItemView(context: Context) : SummonerSuperItemView(context) {

    var summoner: SummonerResponse? = null
    var match: MatchResponse? = null
    var participant: Participant? = null

    init {
        View.inflate(context, R.layout.view_summoner_matchlist_item_match, this)
    }

    override fun setup(match: MatchResponse?) {
        this.summoner = SessionController.getInstance().summonerSearchResult?.summoner
        this.match = match

        determineParticipant()

        if (this.participant?.stats?.win == true) {
            matchItemBackground.setBackgroundColor(resources.getColor(R.color.win))
        } else {
            matchItemBackground.setBackgroundColor(resources.getColor(R.color.lose))
        }

    }

    private fun determineParticipant() {
        val id = summoner?.accountId

        match?.participantIdentities?.forEach {

            if (it.player.accountId == id) {
                val participantId = it.participantId
                match?.participants?.forEach {
                    // participant is found
                    if (it.participantId == participantId) {
                        participant = it
                    }
                }
            }

        }
    }

    private fun populateScore() {

    }

}
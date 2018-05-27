package com.buur.frederikapp.fragments.summoner.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bumptech.glide.Glide
import com.buur.frederikapp.R
import com.buur.frederikapp.controllers.SessionController
import com.buur.frederikapp.extensions.addChampionImagePath
import com.buur.frederikapp.models.Champion
import com.buur.frederikapp.models.Version
import com.buur.frederikapp.modelsapi.SummonerResponse
import com.buur.frederikapp.modelsapi.match.MatchResponse
import com.buur.frederikapp.modelsapi.match.Participant
import io.realm.Realm
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
        determineWin()
        populateScore()
        populateChampion()
        populateSummonerSpells()
        populateItems()

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

    private fun determineWin() {
        if (this.participant?.stats?.win == true) {
            matchItemBackground.setBackgroundColor(resources.getColor(R.color.win))
        } else {
            matchItemBackground.setBackgroundColor(resources.getColor(R.color.lose))
        }
    }

    private fun populateScore() {
        val kills = participant?.stats?.kills
        val deaths = participant?.stats?.deaths
        val assists = participant?.stats?.assists

        championScore.text = "$kills/$deaths/$assists"
    }

    private fun populateChampion() {

        val id = participant?.championId

        championLevel.text = participant?.stats?.champLevel.toString()

        Realm.getDefaultInstance().use { realm ->
            realm.where(Champion::class.java).equalTo("id", id).findFirst()?.let { champion ->
                realm.where(Version::class.java).findFirst()?.version?.let { version ->
                    Glide.with(context)
                            .load(champion.image?.full?.addChampionImagePath(version))
                            .into(championImage)
                }
            }
        }
    }

    private fun populateSummonerSpells() {

    }

    private fun populateItems() {

    }

}
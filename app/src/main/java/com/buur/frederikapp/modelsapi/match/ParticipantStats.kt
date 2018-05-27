package com.buur.frederikapp.modelsapi.match

class ParticipantStats(

        val win: Boolean,
        val champLevel: Int,
        val kills: Int,
        val deaths: Int,
        val assists: Int,

        val objectivePlayerScore: Int,
        val combatPlayerScore: Int,

        val item0: Int,
        val item1: Int,
        val item2: Int,
        val item3: Int,
        val item4: Int,
        val item5: Int,
        val item6: Int

) {
}
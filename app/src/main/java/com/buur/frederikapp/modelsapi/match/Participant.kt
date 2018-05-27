package com.buur.frederikapp.modelsapi.match

class Participant(
        val stats: ParticipantStats,
        val participantId: Int,
//        val runes: List[RuneDto],
//        val timeline: ParticipantTimelineDto,
        val teamId: Int,
        val spell2Id: Int,
//        val masteries: List[MasteryDto],
        val highestAchievedSeasonTier: String,
        val spell1Id: Int,
        val championId: Int
) {
}
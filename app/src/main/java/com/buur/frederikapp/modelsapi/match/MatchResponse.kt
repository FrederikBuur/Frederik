package com.buur.frederikapp.modelsapi.match

class MatchResponse(
        val seasonId: Int,
        val queueId: Int,
        val gameId: Long,
        val participantIdentities: List<ParticipantIdentity>,
        val gameVersion: String,
        val platformId: String,
        val gameMode: String,
        val mapId: Int,
        val gameType: String,
//        val teams: List<TeamStatsDto>,
        val participants: List<Participant>,
        val gameDuration: Long,
        val gameCreation: Long
) {
}
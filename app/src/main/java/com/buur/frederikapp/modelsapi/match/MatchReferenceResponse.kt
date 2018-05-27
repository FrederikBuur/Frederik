package com.buur.frederikapp.modelsapi.match

class MatchReferenceResponse(
        val lane: String,
        val gameId: Long,
        val champion: Int,
        val platformId: String,
        val timestamp: Long,
        val queue: Int,
        val role: String,
        val season: Int
) {
}
package com.buur.frederikapp.modelsapi.match

class MatchListResponse(
        val matches: ArrayList<MatchReferenceResponse>,
        var totalGames: Int,
        var beginIndex: Int,
        var endIndex: Int
) {
}
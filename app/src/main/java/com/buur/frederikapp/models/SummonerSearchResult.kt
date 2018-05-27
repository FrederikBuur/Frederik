package com.buur.frederikapp.models

import com.buur.frederikapp.modelsapi.match.MatchResponse
import com.buur.frederikapp.modelsapi.SummonerResponse

class SummonerSearchResult {

    var summoner: SummonerResponse? = null
    var matchList = ArrayList<MatchResponse>()

}
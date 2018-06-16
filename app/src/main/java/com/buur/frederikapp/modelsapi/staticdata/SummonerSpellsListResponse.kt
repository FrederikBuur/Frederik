package com.buur.frederikapp.modelsapi.staticdata

import com.buur.frederikapp.models.SummonerSpell

class SummonerSpellsListResponse(
        val type: String,
        var version: String,
        var data: Map<String, SummonerSpell>
) {
}
package com.buur.frederikapp.modelsapi.staticdata

import com.buur.frederikapp.models.Champion

class ChampionListResponse(
        val type: String,
        var version: String,
        var data: Map<Int, Champion>
) {
}
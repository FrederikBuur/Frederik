package com.buur.frederikapp.controllers

import com.buur.frederikapp.api.ServiceGenerator
import com.buur.frederikapp.api.interfaces.IStaticData
import com.buur.frederikapp.models.SummonerSearchResult

class SessionController {

    internal var summonerSearchResult: SummonerSearchResult? = null

    companion object {
        private var instanceCompanion: SessionController? = null

        fun getInstance(): SessionController {
            if (instanceCompanion == null) {
                instanceCompanion = SessionController()
            }
            return instanceCompanion as SessionController
        }
    }
}
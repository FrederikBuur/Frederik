package com.buur.frederikapp.controllers

import com.buur.frederikapp.modelsapi.SummonerResponse

class SessionController {

    internal var summoner: SummonerResponse? = null

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
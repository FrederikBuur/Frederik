package com.buur.frederikapp.controllers

import com.buur.frederikapp.api.ErrorHandler
import com.buur.frederikapp.api.ServiceGenerator
import com.buur.frederikapp.api.ServiceGenerator.Companion.API_KEY
import com.buur.frederikapp.api.interfaces.ISummoner
import com.buur.frederikapp.models.SummonerSearchResult
import com.buur.frederikapp.modelsapi.match.MatchResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SummonerController {

    private val TAG = "SummonerController"
    private var summonerClient: ISummoner? = null

    private fun getSummonerClient(): ISummoner {
        if (summonerClient == null) {
            summonerClient = ServiceGenerator().createSummonerAPI()
        }
        return summonerClient!!
    }

    fun fetchSummoner(name: String) : Observable<MatchResponse> {

        if (SessionController.getInstance().summonerSearchResult == null) {
            SessionController.getInstance().summonerSearchResult = SummonerSearchResult()
        }

        // Fetching summoner
        return getSummonerClient().getSummoner(name, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMap { summonerResponse ->
                    SessionController.getInstance().summonerSearchResult?.summoner = summonerResponse

                    // clearing match least from potential old search result
                    SessionController.getInstance().summonerSearchResult?.matchList?.clear()

                    // Fetching summoners match list by index
                    getSummonerClient().getMatchlistWithIndex(
                            summonerResponse.accountId,
                            0L,
                            10L,
                            API_KEY)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                }
                .concatMap { matchListResponse ->

                    // Fetching match references from match list
                    Observable.fromIterable(matchListResponse.matches)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .concatMap {  matchReferenceResponse ->

                                // Fetching match details from individual match reference
                                getSummonerClient().getMatchById(
                                        matchReferenceResponse.gameId,
                                        API_KEY
                                        )
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                            }
                }
                .doOnNext { matchResponse ->

                    // saving summoners matches in session controller
                    SessionController.getInstance().summonerSearchResult?.matchList?.add(matchResponse)
                }
                .doOnError{
                    ErrorHandler.handleError(it)
                }
    }

}
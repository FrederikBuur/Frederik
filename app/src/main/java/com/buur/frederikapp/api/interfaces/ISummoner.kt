package com.buur.frederikapp.api.interfaces

import com.buur.frederikapp.modelsapi.match.MatchListResponse
import com.buur.frederikapp.modelsapi.match.MatchResponse
import com.buur.frederikapp.modelsapi.SummonerResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ISummoner {

    @GET("lol/summoner/v3/summoners/by-name/{name}")
    fun getSummoner(
            @Path("name") name: String,
            @Query("api_key") api_key: String
    ): Observable<SummonerResponse>

    @GET("/lol/match/v3/matchlists/by-account/{accountId}")
    fun getMatchlistWithIndex(
            @Path("accountId") accountId: Long,
            @Query("beginIndex") beginIndex: Long,
            @Query("endIndex") endIndex: Long,
            @Query("api_key") api_key: String
    ): Observable<MatchListResponse>

    @GET("/lol/match/v3/matches/{matchId}")
    fun getMatchById(
            @Path("matchId") matchId: Long,
            @Query("api_key") api_key: String
    ): Observable<MatchResponse>
}
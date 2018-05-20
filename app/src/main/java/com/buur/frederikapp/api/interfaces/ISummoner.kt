package com.buur.frederikapp.api.interfaces

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
    ) : Observable<SummonerResponse>

}
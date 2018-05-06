package com.buur.frederikapp.api.interfaces

import com.buur.frederikapp.modelsapi.ChampionListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IChampions {

    @GET("/lol/static-data/v3/champions")
    fun getChampionList(
            @Query("api_key") api_key: String,
            @Query("champListData") champListData: String,
            @Query("dataById") dataById: Boolean
    ): Observable<ChampionListResponse>

}
package com.buur.frederikapp.api.interfaces

import com.buur.frederikapp.modelsapi.ChampionListResponse
import com.buur.frederikapp.modelsapi.ItemListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IStaticData {

    @GET("/lol/static-data/v3/champions")
    fun getChampionList(
            @Query("api_key") api_key: String,
            @Query("champListData") champListData: String,
            @Query("dataById") dataById: Boolean
    ): Observable<ChampionListResponse>

    @GET("/lol/static-data/v3/versions")
    fun getVersions(
            @Query("api_key") api_key: String
    ): Observable<ArrayList<String>>

    @GET("/lol/static-data/v3/items")
    fun getItems(
            @Query("api_key") api_key: String
    ) : Observable<ItemListResponse>

}
package com.example.bird_app

import com.example.birdnest_apk.models.ebird.ObservationsItem
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface ebird {
    @GET("/v2/data/obs/geo/recent")
    suspend fun getBirdInfo(@Query("lat") lat:String, @Query("lng") lng:String, @Query("key") key:String ): Response<List<ObservationsItem>>

}
package com.expeknow.store.network

import retrofit2.Call
import retrofit2.http.GET

interface StoreService {

    @GET(".")
    fun getAllApps() : Call<AppData>

    @GET("featured")
    fun getFeaturedApps() : Call<AppData>
}
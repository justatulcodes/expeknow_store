package com.expeknow.store.network

import retrofit2.Call
import retrofit2.http.GET

interface StoreService {

    @GET(".")
    fun getAllApps() : Call<AppData>

    @GET(".")
    fun getFeaturedApps() : Call<AppData>
}
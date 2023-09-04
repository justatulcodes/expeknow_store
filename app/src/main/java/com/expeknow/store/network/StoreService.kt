package com.expeknow.store.network

import retrofit2.Call
import retrofit2.http.GET


interface StoreService {
    @GET("s/AKfycbzSE-Z8hpxu9fK5eSxTk7rXiR8OLZGRtsh3jb3we7OvvcR8vJAySluZ5UWvjxnoIPo1/exec")
    fun getAllApps() : Call<AppData>

    @GET("featured")
    fun getFeaturedApps() : Call<AppData>
}
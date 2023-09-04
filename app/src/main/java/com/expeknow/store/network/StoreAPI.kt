package com.expeknow.store.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.gson.GsonConverterFactory

class StoreAPI {

    private val baseUrl = "https://script.google.com/macros/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // You can also you GsonConvertorfactory here
    // GsonConverterFactory.create()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory( MoshiConverterFactory.create(moshi))
        .baseUrl(baseUrl)
        .build()

    val store : StoreService by lazy {
        retrofit.create(StoreService::class.java)
    }

    // This will work but it is not optimized
//    val store : StoreService = retrofit.create(StoreService::class.java)
}
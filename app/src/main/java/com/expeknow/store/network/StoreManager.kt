package com.expeknow.store.network

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreManager {

    private val _applist_response = mutableStateOf(AppData())
    val appList : State<AppData>
        @Composable get() = remember {_applist_response}

    private val _featuredApps_response = mutableStateOf(AppData())
    val featuredApps : State<AppData>
        @Composable get() = remember {
            _featuredApps_response
        }

    init {
            getAllApps()
            getFeaturedApps()
    }

    fun getAllApps() {
        val service = StoreAPI().store.getAllApps()
        service.enqueue(object : Callback<AppData>{
            override fun onResponse(call: Call<AppData>, response: Response<AppData>) {
                if(response.isSuccessful) {
                    _applist_response.value = response.body()!!
                }else{
                    Log.d("storeapi error", response.errorBody().toString())
                }
            }
            override fun onFailure(call: Call<AppData>, t: Throwable) {
                Log.d("storeapi error", t.message.toString())
            }

        })
    }

    fun getFeaturedApps() {
        val service = StoreAPI().store.getFeaturedApps()
        service.enqueue(object : Callback<AppData>{
            override fun onResponse(call: Call<AppData>, response: Response<AppData>) {
                if(response.isSuccessful){
                    _featuredApps_response.value = response.body()!!
                }else{
                    Log.d("storeapi error", response.errorBody().toString())
                }
            }
            override fun onFailure(call: Call<AppData>, t: Throwable) {
                Log.d("storeapi error", t.message.toString())
            }

        })
    }
}
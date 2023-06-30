package com.expeknow.store.network

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import com.expeknow.store.Constants
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

    @SuppressLint("MutableCollectionMutableState")
    private var _searchedApps = mutableStateOf(ArrayList<App>())
    val searchedAppsList : State<List<App>>
        @Composable get() = remember {
            _searchedApps
        }

    @SuppressLint("MutableCollectionMutableState")
    private var _suggestedApps = mutableStateOf(ArrayList<App>())
    val searchedApps : State<List<App>>
        @Composable get() = remember {
            _suggestedApps
        }



    init {
            getAllApps()
//            getFeaturedApps()
    }


    fun getSearchApps(searchKey: String){
        val allAppsList = _applist_response.value.apps
        val results = ArrayList<App>()
        if(allAppsList != null){
            for(app in allAppsList){
                if(app.appName!!.contains(searchKey, ignoreCase = true) ||
                    app.tagLine!!.contains(searchKey, ignoreCase = true)
                        ) {
                    results.add(app)
                }
            }
            _searchedApps.value = results
            fillSuggestedApps()
        }


    }

    private fun fillSuggestedApps() {
        val searchedApps = _searchedApps.value
        val allApps = _applist_response.value.apps
        val results = ArrayList<App>()
        for(app in searchedApps){
            if(!allApps!!.contains(app)){
                results.add(app)
            }
        }
        _suggestedApps.value = results
    }

    fun getAllApps() {
        val service = StoreAPI().store.getAllApps()
        Log.d("sucess", "entered function")
        service.enqueue(object : Callback<AppData>{
            override fun onResponse(call: Call<AppData>, response: Response<AppData>) {
                if(response.isSuccessful) {
                    _applist_response.value = response.body()!!
                    Log.d("sucess", response.errorBody().toString())
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
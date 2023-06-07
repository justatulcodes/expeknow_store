package com.expeknow.store


import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.expeknow.store.network.StoreManager
import com.expeknow.store.ui.windows.DetailsPage
import com.expeknow.store.ui.windows.ProfilePage
import com.expeknow.store.ui.windows.Store
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.*


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppStore(scrollState: ScrollState, navController: NavHostController) {

    val storeManager = StoreManager()


    Scaffold {
        NavHost(navController = navController,
            startDestination = NavigationScreens.Store.route ) {
            composable(NavigationScreens.Store.route) {
                Store(scrollState = scrollState, navController = navController, storeManager = storeManager)
            }
            composable(NavigationScreens.Details.route,
            arguments = listOf(navArgument("id"){type= NavType.IntType})
            ){
                val id = it.arguments?.getInt("id")!!
                DetailsPage(navController = navController, storeManager = storeManager, appId = id)
            }
            composable(NavigationScreens.Profile.route){
                ProfilePage(navController = navController)
            }
        }
    }
}
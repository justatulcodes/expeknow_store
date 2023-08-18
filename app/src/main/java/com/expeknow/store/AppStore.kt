package com.expeknow.store


import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.expeknow.store.network.App
import com.expeknow.store.network.StoreManager
import com.expeknow.store.ui.windows.DetailsPage
import com.expeknow.store.ui.windows.ProfilePage
import com.expeknow.store.ui.windows.SearchResultsPage
import com.expeknow.store.ui.windows.ShowScreenshotView
import com.expeknow.store.ui.windows.Store
import kotlinx.coroutines.*


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppStore() {

    val navController = rememberNavController()
    val scrollState : ScrollState = rememberScrollState()
    val storeManager = remember { StoreManager() }


    Scaffold {
        NavHost(navController = navController,
            startDestination = NavigationScreens.Store.route ) {

            // Route to go to home page
            composable(NavigationScreens.Store.route) {
                Store(scrollState = scrollState, navController = navController, storeManager = storeManager)
            }

            // Route to go to details page
            composable(NavigationScreens.Details.route,
            arguments = listOf(navArgument("id"){type= NavType.IntType})
            ){
                val id = it.arguments?.getInt("id")!!
                lateinit var appData : App
                val appList = storeManager.appList.value.apps!!
                for (i in appList.indices){
                    if(appList[i].appId == id){
                        appData =  appList[i]
                    }
                }
                DetailsPage(navController = navController, appData = appData)
            }

            // Route to go in Profile page
            composable(NavigationScreens.Profile.route){
                ProfilePage(navController = navController, scrollState)
            }

            // Route to go in screenshot page
            composable(NavigationScreens.Screenshot.route,
            arguments = listOf(navArgument("link"){type= NavType.StringType})
            ){
                val encodedUrl = it.arguments?.getString("link")
                ShowScreenshotView(navController = navController,
                    screenshotLink = encodedUrl!!)
            }

            // Route to go in search page
            composable(NavigationScreens.Search.route){
                SearchResultsPage(navController = navController, storeManager = storeManager)
            }
        }
    }
}
package com.expeknow.store

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.expeknow.store.ui.windows.DetailsPage
import com.expeknow.store.ui.windows.ProfilePage
import com.expeknow.store.ui.windows.Store

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppStore(scrollState: ScrollState, navController: NavHostController) {
    Scaffold {
        NavHost(navController = navController,
            startDestination = NavigationScreens.Store.route ) {
            composable(NavigationScreens.Store.route) {
                Store(scrollState = scrollState, navController = navController)
            }
            composable(NavigationScreens.Details.route){
                DetailsPage(navController = navController)
            }
            composable(NavigationScreens.Profile.route){
                ProfilePage(navController = navController)
            }
        }
    }
}
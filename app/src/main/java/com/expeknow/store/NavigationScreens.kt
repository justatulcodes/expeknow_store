package com.expeknow.store

sealed class NavigationScreens(val title: String, val route: String){
    object Store: NavigationScreens("Store", Constants().store)
    object Details : NavigationScreens("Details", Constants().details)
    object Profile : NavigationScreens("Profile", Constants().profile)
    object Screenshot : NavigationScreens("Screenshot", Constants().screenshot)

}

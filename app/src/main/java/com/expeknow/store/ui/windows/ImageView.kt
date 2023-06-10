package com.expeknow.store.ui.windows

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.expeknow.store.widgets.TopBar
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowScreenshotView(navController: NavController, screenshotLink: String) {
    
    Scaffold(topBar = { TopBar(navController = navController) },
        containerColor = Color.Black) {
        val zoomState = rememberZoomState()
        Log.d("screenshot", screenshotLink)
        AsyncImage(model = screenshotLink,
            contentDescription = "screenshot",
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .zoomable(zoomState),
        )
    }

}


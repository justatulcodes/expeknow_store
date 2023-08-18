package com.expeknow.store.ui.windows

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowScreenshotView(navController: NavController, screenshotLink: String) {
    
    Scaffold(topBar = { TopBarCustom(navController = navController) },
        containerColor = Color.Black) {
        val zoomState = rememberZoomState()
        AsyncImage(model = screenshotLink,
            contentDescription = "screenshot",
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .zoomable(zoomState),
            contentScale = ContentScale.Inside
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCustom(navController: NavController) {
    CenterAlignedTopAppBar(
        title = { Text(text = "")},
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBackIos,
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                        .padding(start = 8.dp),
                    tint = MaterialTheme.colorScheme.background)
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
        )
    )
}


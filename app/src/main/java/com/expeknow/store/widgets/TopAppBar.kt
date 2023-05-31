package com.expeknow.store.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * Creates a common top app bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    CenterAlignedTopAppBar(
        title = { Text(text = "") },
        navigationIcon = {
            IconButton(
                onClick = {navController.popBackStack()} ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIos,
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                        .padding(start = 8.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = {
            }) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "more options"
                )
            }
        }

    )
}
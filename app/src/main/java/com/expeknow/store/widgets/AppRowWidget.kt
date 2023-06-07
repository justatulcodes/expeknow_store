package com.expeknow.store.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.expeknow.store.NavigationScreens
import com.expeknow.store.network.App
import com.skydoves.landscapist.coil.CoilImage

/**
 * Creates an app list row which displays icons of the app and thier name below
 * for the list of apps provided.
 */
@Composable
fun AppListRow(appList: List<App>, heading: String, navController: NavController,
               modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(top = 10.dp)
    ) {
        Column{
            AppListRowHeader(heading = heading, modifier = modifier)
            LazyRow {
                items (appList.size){
                        index ->
                    Column(Modifier.padding(6.dp)) {
                        CommonAppTemplate(
                            navController = navController,
                            appData = appList[index],
                            modifier = modifier
                        )

                    }
                }
            }

        }
    }

}

/**
 * Common Widget for App list row header
 */
@Composable
fun AppListRowHeader(heading: String, modifier: Modifier = Modifier){
    Text(text = heading,
        fontSize = 25.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.1.sp,
        modifier = modifier.padding(start = 10.dp, bottom = 6.dp)
    )
}


/**
 * Common app square icon template.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CommonAppTemplate(navController: NavController, appData: App,
                      modifier: Modifier = Modifier
) {
    Box(
        modifier
            .width(100.dp)
            .height(100.dp)
            .clickable {
                navController.navigate("details/${appData.appId}")
            }) {
        Card(shape = RoundedCornerShape(20.dp),
            onClick = { navController.navigate("details/${appData.appId}")}) {
            CoilImage(imageModel = appData.icon,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
    Text(text = appData.appName!!,
        modifier = modifier.padding(6.dp).width(90.dp),
        fontSize = 14.sp,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )

}
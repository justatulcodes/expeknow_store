package com.expeknow.store.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.expeknow.store.R
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
        style = MaterialTheme.typography.displaySmall,
        modifier = modifier.padding(start = 10.dp, bottom = 6.dp, top = 8.dp)
    )
}


/**
 * Common app square icon template.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonAppTemplate(navController: NavController, appData: App,
                      modifier: Modifier = Modifier
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }
    Box(
        modifier
            .width(100.dp)
            .height(100.dp)
            .clickable {
                navController.navigate("details/${appData.appId}")
            }) {
        Card(shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(3.dp),
            onClick = { navController.navigate("details/${appData.appId}")}) {
            CoilImage(imageModel = appData.icon,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
    Column(modifier = Modifier.clickable(interactionSource = interactionSource, indication = null){
        navController.navigate("details/${appData.appId}")
    }) {
        Text(text = appData.appName!!,
            modifier = modifier
                .padding(horizontal = 6.dp, vertical = 5.dp)
                .width(90.dp),
            fontSize = 15.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        Row(
            modifier
                .padding(horizontal = 6.dp, vertical = 0.dp)
                ) {
            Text(text = "${appData.size} MB |",
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
            )
            Icon(imageVector = Icons.Filled.Bolt,
                contentDescription = "stars",
                tint = colorResource(id = R.color.bolt_color),
                modifier = Modifier.size(16.dp))

            Text(text = "${appData.complexity}",
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
            )
        }
    }


}
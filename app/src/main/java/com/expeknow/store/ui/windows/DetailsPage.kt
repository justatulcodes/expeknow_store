package com.expeknow.store.ui.windows

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.expeknow.store.NavigationScreens
import com.expeknow.store.R
import com.expeknow.store.network.App
import com.expeknow.store.network.StoreManager
import com.expeknow.store.widgets.AppListRow
import com.expeknow.store.widgets.TopBar
import com.skydoves.landscapist.coil.CoilImage


@ExperimentalMaterial3Api
@Composable
fun DetailsPage(navController: NavController, storeManager: StoreManager, appId : Int) {

    val appList = storeManager.appList.value
    lateinit var appData : App
    for (i in 0 until appList.apps!!.size){
        if(appList.apps[i].appId == appId){
           appData =  appList.apps[i]
        }
    }

    val scrollState = rememberScrollState()

    Scaffold(topBar = { TopBar(navController) },
    ) {
        Column(modifier = Modifier
            .padding(it)
            .padding(5.dp)
            .verticalScroll(scrollState)) {

            //App logo, name, publisher and tags
            Row {
                    Card(modifier = Modifier
                        .size(120.dp)
                        .padding(10.dp),
                    shape = RoundedCornerShape(20.dp)
                    ) {
                        CoilImage(imageModel = appData.icon,
                            contentDescription = "app logo")
                    }
                    Box(modifier = Modifier.align(CenterVertically)){
                        Column {
                            Text(text = appData.appName!!,
                                fontSize = 22.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.sp,
                                modifier = Modifier.padding(start = 10.dp, bottom = 5.dp)
                            )
                            Text(text = "By Expeknow",
                                fontSize = 14.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 0.sp,
                                color = Color.LightGray,
                                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
                            )
                            Row(modifier = Modifier.padding(start = 10.dp)) {
                                for(index in 0..appData.tags!!.size-2){
                                    AppTagCard(tag = appData.tags!![index])
                                }
                                AppTagCard(tag = appData.tags!![appData.tags!!.size-1],
                                    modifier = Modifier.padding(horizontal = 5.dp))
                            }
                        }
                    }

                }
            //app stats row
            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)) {

                AppStatsText(statText = "${appData.size} MB")
                AppStatsText(statText = "12K Downloads")
                AppStatsText(statText = "43 Reviews")
            }

            //Download button
            Button(onClick = { },
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
            ),
            ) {
                Text(text = "Download",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(3.dp)
                )
            }

            //Video card
            Box(modifier = Modifier.padding(10.dp)) {
                VideoCard(videoLink = "")
            }

            //Description of the app
            Text(text = appData.description!!,
            fontSize = 14.sp,
            color = Color.Gray,
                modifier = Modifier.padding(20.dp))


            //App screenshots
            LazyRow(modifier = Modifier.padding(0.dp)) {
                items(appData.screenshot!!.size){
                    AppScreenshot(imageLink = appData.screenshot!![it])
                }
            }

            //other apps row
//            AppListRow(appList = Apps(), heading = "Other Apps", navController = navController)

            //End credits
            Box(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .align(CenterHorizontally)){
                Text(text = "Expeknow Store",
                color =  Color.LightGray,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                letterSpacing = 2.sp
                )
            }
        }
    }
}


@Composable
fun AppTagCard(tag: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray,
            contentColor = Color.DarkGray
        )
    ) {
        Text(text = tag,
            fontSize = 10.sp,
            modifier = Modifier.padding(3.dp))
    }
}

@Composable
fun VideoCard(videoLink: String) {
    Card(shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.Gray
        )) {
        Icon(imageVector = Icons.Filled.PlayArrow,
            contentDescription = "play video",
            modifier = Modifier.padding(20.dp))
    }
}

@Composable
fun AppScreenshot(imageLink: String) {
    Card(
        Modifier
            .padding(5.dp)
            .height(333.dp)
            .width(150.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        CoilImage(imageModel = imageLink,
            contentDescription = "", contentScale = ContentScale.Crop)
    }
}

@Composable
fun AppStatsText(statText: String){
    Box {
        Text(text = statText,
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.sp,
            color = Color.LightGray,
            modifier = Modifier
                .padding(start = 10.dp, bottom = 10.dp)
        )
    }
}

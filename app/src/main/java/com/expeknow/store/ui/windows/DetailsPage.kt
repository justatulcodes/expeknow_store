package com.expeknow.store.ui.windows

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.icons.rounded.OpenInNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.expeknow.store.R
import com.expeknow.store.network.App
import com.expeknow.store.widgets.TopBar
import com.expeknow.store.widgets.small.EndCredits
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.skydoves.landscapist.coil.CoilImage
import java.net.URLEncoder


@ExperimentalMaterial3Api
@Composable
fun DetailsPage(navController: NavController, appData: App) {

    val scrollState = rememberScrollState()
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Scaffold(
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopBar(navController)
            }
        },
    ) {
        Column(modifier = Modifier
            .padding(it)
            .padding(horizontal = 5.dp)
            .verticalScroll(scrollState)) {

            Spacer(modifier = Modifier.height(10.dp))
            //App logo, name, publisher and tags
            Row {
                    Card(
                        modifier = Modifier
                            .size(120.dp)
                            .padding(10.dp)
                            .clickable(interactionSource = interactionSource, indication = null) {
                                val encodedUrl = URLEncoder.encode(appData.icon, "UTF-8")
                                navController.navigate("screenshotPage/${encodedUrl}")
                            },
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
//                            containerColor = Color.Transparent
                        ),
                    ) {
                        CoilImage(imageModel = appData.icon,
                            contentDescription = "app logo",)
                    }
                    Box(modifier = Modifier.align(CenterVertically)){
                        Column {
                            Text(text = appData.appName!!,
                                fontSize = 22.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.sp,
                                modifier = Modifier.padding(start = 5.dp, bottom = 5.dp)
                            )
                            Text(text = "By Expeknow",
                                fontSize = 14.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 0.sp,
                                modifier = Modifier.padding(start = 5.dp, bottom = 10.dp)
                            )
                            Row {
                                for(tag in appData.tags!!.listIterator()){
                                    AppTagCard(tag = tag,
                                        modifier = Modifier.padding(horizontal = 5.dp))
                                }
                            }
                        }
                    }

                }
            //app stats row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = CenterVertically

            ) {
                Row {
                    AppStatsText(statText = "Size: ${appData.size} MB")

                }
                Row {
                    AppStatsText(statText = "Difficulty: ")
                    repeat(appData.complexity!!) {
                        Icon(imageVector = Icons.Filled.Bolt, contentDescription = "",
                            tint = colorResource(id = R.color.bolt_color),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

            }

            //Download button

            var isDownloading by remember {
                mutableStateOf(false)
            }
            Button(onClick = { isDownloading = !isDownloading },
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
//            colors = ButtonDefaults.buttonColors(
//                backgroundColor = Color.Black,
//            ),
            ) {
                Icon(imageVector = Icons.Rounded.Download, contentDescription = "",)
                AnimatedVisibility(visible = isDownloading) {
                    Text(text = "Downloading",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 3.dp)
                    )
                }
            }

            //App screenshots
            LazyRow(modifier = Modifier.padding(horizontal = 5.dp)) {
                items(appData.screenshot!!.size){
                    AppScreenshot(imageLink = appData.screenshot[it], navController = navController)
                }
            }

            //Description Box
            Box(modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)){
                DescriptionBox(appData = appData)
            }

            //Video card
            Box(modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) {
                VideoCard(videoId = "_OwNFUaAXfk")
            }
            
            val mUriHandler = LocalUriHandler.current 
            Button(onClick = { mUriHandler.openUri("https://youtu.be/_OwNFUaAXfk") },
//            colors = ButtonDefaults.buttonColors(
//                backgroundColor = Color.Black,
//            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Row(modifier = Modifier.fillMaxSize(),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                    Text(text = "Open in youtube", fontSize = 15.sp,
                    )
                    Icon(imageVector = Icons.Rounded.OpenInNew,
                        contentDescription = "open in youtube ",
                        modifier = Modifier
                            .size(18.dp)
                            .padding(start = 3.dp))
                }
            }


            //other apps row
//            AppListRow(appList = Apps(), heading = "Other Apps", navController = navController)

            //End credits
            EndCredits()
        }
    }
}


@Composable
fun DescriptionBox(appData: App) {

    var isExpanded by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember { MutableInteractionSource() }

    Column(modifier = Modifier.fillMaxWidth()) {
        Card(shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(2.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {
            Text(text = appData.tagLine!!,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable(interactionSource = interactionSource, indication = null) {
                        isExpanded = !isExpanded
                    }
            )
            AnimatedVisibility(visible = isExpanded,
            ) {
                Text(text = "\n"+appData.description!!,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
                )
            }
        }

        Button(onClick = { isExpanded = !isExpanded },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            elevation = ButtonDefaults.buttonElevation(0.dp)
        ) {
            if(isExpanded){
                Text(text = "Read Less", fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold)
                Icon(imageVector = Icons.Rounded.ExpandLess, contentDescription = "",
                    Modifier
                        .size(22.dp)
                        .padding(3.dp))
            }

            else{
                Text(text = "Read More", fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold)
                Icon(imageVector = Icons.Rounded.ExpandMore, contentDescription = "",
                    Modifier
                        .size(22.dp)
                        .padding(3.dp))
            }

        }
    }

}

@Composable
fun AppTagCard(tag: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
    ) {
        Text(text = tag,
            fontSize = 10.sp,
            modifier = Modifier.padding(3.dp))
    } }

@Composable
fun VideoCard(videoId: String) {
    Card(shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
    ) {
        AndroidView(factory = {
            val view = YouTubePlayerView(it)
            val iFramePlayerOptions = IFramePlayerOptions.Builder()
            iFramePlayerOptions.fullscreen(1)
            view.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.cueVideo(videoId, 0f)
                    }

                }
            )
            view
        })

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreenshot(imageLink: String, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .height(222.dp)
            .width(100.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            val url = URLEncoder.encode(imageLink, "UTF-8")
            navController.navigate("screenshotPage/${url}")
        }
    ) {
        CoilImage(imageModel = imageLink,
            contentDescription = "", contentScale = ContentScale.Crop)
    }
}

@Composable
fun AppStatsText(statText: String){
    Box {
        Column(
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
            Text(text = statText,
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
            )
        }

    }
}


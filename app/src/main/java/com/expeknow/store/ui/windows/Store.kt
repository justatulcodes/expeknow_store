package com.expeknow.store.ui.windows

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.expeknow.store.NavigationScreens
import com.expeknow.store.R
import com.expeknow.store.network.App
import com.expeknow.store.network.AppData
import com.expeknow.store.network.StoreManager
import com.expeknow.store.widgets.AppListRow
import com.expeknow.store.widgets.ShimmeringAppRow
import com.expeknow.store.widgets.ShimmeringFeaturedAppRow
import com.expeknow.store.widgets.small.EndCredits
import com.expeknow.store.widgets.small.SearchBar
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
fun Store(scrollState: ScrollState, navController: NavController, storeManager : StoreManager) {

//    val featuredApps : AppData = storeManager.featuredApps.value
    val allApps : AppData = storeManager.appList.value
    val featuredApps : ArrayList<App> = ArrayList()
    if(allApps.apps != null){
        for(app in allApps.apps.listIterator()){
            if(app.isFeatured == 1){
                featuredApps.add(app)
            }
        }
    }

    val searchBarText = remember {
        mutableStateOf("Loading store...")
    }

    val interactionSource = remember {
        MutableInteractionSource()
    }
    val scope = rememberCoroutineScope()
    val appRowScrollState = rememberScrollState()
    val isAutoAppScrollRunning = remember {
      mutableStateOf(false)
    }

    Scaffold(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(it)
                .verticalScroll(scrollState)
        ) {

            //Search bar row
            Row(
                Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp),
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable(interactionSource = interactionSource, indication = null)
                            {   if(allApps.apps != null){
                                navController.navigate(NavigationScreens.Search.route)
                            } },
                            verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Rounded.Search, contentDescription = "",
                            Modifier
                                .size(35.dp)
                                .padding(5.dp))
                        Text(text = searchBarText.value, color = Color.LightGray,
                        fontSize = 16.sp, modifier = Modifier.fillMaxHeight())
                    }
                }

                Card(shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .size(55.dp)
                        .padding(0.dp),
                    onClick = {
                                navController.navigate(NavigationScreens.Profile.route)  }) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_pic),
                        contentDescription = "profile pic",
                    )
                }

            }

            if(allApps.apps != null){

                searchBarText.value = "Search Apps..."

                //Developer's Choice
                Row(Modifier.padding(top = 15.dp, bottom = 10.dp)) {
                    Column {
                        DevChoiceRowHeader(heading = "Expeknow's Choice",
                            modifier = Modifier.padding(top = 0.dp))
                        Row(Modifier.horizontalScroll(appRowScrollState)) {
                            repeat (featuredApps.size){
                                    index ->
                                Column {
                                    DevChoiceAppTemplate(navController = navController,
                                        appData = featuredApps[index], interactionSource = interactionSource)
                                }
                            }
                        }

                    }
                }

                //Newly Released Apps Section
                val appsReversed = allApps.apps
                AppListRow(appList = appsReversed.reversed(), heading = "Newly Released",
                    navController = navController)

                //Old Apps
                AppListRow(appList = allApps.apps, heading = "Old Apps",
                    navController = navController)

                //Under Development
                AppListRow(appList = allApps.apps, heading = "Under Development",
                    navController = navController)

                //End Credits
                EndCredits()

                //Moves featured app row
                if(!isAutoAppScrollRunning.value){
                    isAutoAppScrollRunning.value = true
                    MoveFeaturedAppRow(appRowScrollState, scope)
                }

            }

           else{
                ShimmeringFeaturedAppRow()
                repeat(3) {
                    ShimmeringAppRow()
                }
            }
        }
    }

}

fun MoveFeaturedAppRow(scrollState: ScrollState, scope : CoroutineScope) {
    Timer().scheduleAtFixedRate(object : TimerTask() {
        override fun run() {
                scope.launch {
                    if(scrollState.value == scrollState.maxValue){
                        scrollState.animateScrollTo(0,
                        animationSpec = tween(500)
                        )
                    }
                    else
                        scrollState.animateScrollBy(560f - (scrollState.value % 560f),
                        animationSpec = tween(800)
                        )
                }
            }
    }, 8000, 5000)

}

@Composable
fun DevChoiceAppTemplate(navController: NavController, appData: App,
                         modifier: Modifier= Modifier, interactionSource: MutableInteractionSource) {
    Box(
        modifier
            .width(280.dp)
            .height(180.dp)
            .padding(6.dp)
            .clickable {
                navController.navigate("details/${appData.appId}")
            },
        ) {
        Card(shape = RoundedCornerShape(15.dp),
        ) {
            CoilImage(imageModel = appData.appGraphic,
                contentDescription = "",
                contentScale = ContentScale.Crop)
        }
    }

    Column(
        Modifier
            .padding(start = 12.dp)
            .clickable(interactionSource = interactionSource, indication = null) {
                navController.navigate("details/${appData.appId}")
            }) {
        Text(text = appData.appName!!,
            fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
        )
        Row(Modifier.padding(start = 0.dp)) {

            Text(text = "${appData.size} MB |",
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
            )
            Icon(imageVector = Icons.Filled.Bolt,
                contentDescription = "stars",
                tint = colorResource(id = R.color.bolt_color),
                modifier = Modifier.size(20.dp))

            Text(text = "${appData.complexity}",
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}


@Composable
fun DevChoiceRowHeader(heading: String, modifier : Modifier) {
    Text(text = heading,
        fontSize = 30.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Black,
        letterSpacing = 0.sp,
        modifier = modifier.padding(start = 10.dp, bottom = 6.dp)
    )
}


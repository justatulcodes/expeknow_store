package com.expeknow.store.ui.windows

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
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
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import java.util.logging.Handler


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
fun Store(scrollState: ScrollState, navController: NavController, storeManager : StoreManager) {

//    val featuredApps : AppData = storeManager.featuredApps.value
    val allApps : AppData = storeManager.appList.value
    val featuredApps : AppData = storeManager.appList.value
    val scope = rememberCoroutineScope()
    val appRowScrollState = rememberScrollState()
    val isUserOnHomePage = remember {
      mutableStateOf(true)
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
                Modifier.padding(horizontal = 10.dp, vertical = 16.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp),
                    elevation = 3.dp
                ) {
                    SearchBar()
                }

                Card(shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .size(55.dp)
                        .padding(0.dp),
                    elevation = 3.dp,
                    onClick = { navController.navigate(NavigationScreens.Profile.route) }) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_pic),
                        contentDescription = "profile pic",
                    )
                }

            }

            if(allApps.apps != null && featuredApps.apps != null){
                //Developer's Choice
                Row(Modifier.padding(top = 20.dp, bottom = 10.dp)) {
                    Column {
                        DevChoiceRowHeader(heading = "Expeknow's Choice",
                            modifier = Modifier.padding(top = 0.dp))
                        Row(Modifier.horizontalScroll(appRowScrollState)) {
                            repeat (featuredApps.apps.size){
                                    index ->
                                Column {
                                    DevChoiceAppTemplate(navController = navController,
                                        appData = featuredApps.apps[index])
                                }
                            }
                        }
//                        Button(onClick = { scope.launch {
//                            appRowScrollState.animateScrollTo(appRowScrollState.value-560)
//                        } }) {
//                            Text(text = "Scroll left")
//                        }
//
//                        Button(onClick = { scope.launch {
//                            appRowScrollState.animateScrollBy(560f)
//                        } }) {
//                            Text(text = "Scroll right")
//                        }

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
                MoveFeaturedAppRow(appRowScrollState, scope)

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
                        scrollState.animateScrollTo(0)
                    }
                    else
                        scrollState.animateScrollBy(560f)
                }
            }
    }, 5000, 5000)

}

@Composable
fun DevChoiceAppTemplate(navController: NavController, appData: App,
                         modifier: Modifier= Modifier) {
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
            elevation = 3.dp
        ) {
            CoilImage(imageModel = appData.appGraphic,
                contentDescription = "",
                contentScale = ContentScale.Crop)
        }
    }

    Column(Modifier.padding(start = 12.dp)) {
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
        fontSize = 32.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Black,
        letterSpacing = 0.sp,
        modifier = modifier.padding(start = 10.dp, bottom = 6.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var inputText by remember {
        mutableStateOf(TextFieldValue(""))
    }

    TextField(
        value = inputText,
        onValueChange = { value ->
            inputText = value
        },
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "search",
                    modifier = Modifier
                        .padding(start = 15.dp, end = 5.dp)
                        .size(25.dp)
                )

        },
        trailingIcon = {
            if (inputText != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        inputText =
                            TextFieldValue("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "erase entered text",
                        modifier = Modifier
                            .padding(start = 15.dp, end = 5.dp)
                            .size(25.dp)
                    )
                }
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
    )
}


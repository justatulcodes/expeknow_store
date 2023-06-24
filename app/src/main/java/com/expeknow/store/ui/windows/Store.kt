package com.expeknow.store.ui.windows

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Thunderstorm
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.expeknow.store.widgets.AppListRowHeader
import com.expeknow.store.widgets.ShimmeringAppRow
import com.expeknow.store.widgets.ShimmeringFeaturedAppRow
import com.skydoves.landscapist.coil.CoilImage


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
fun Store(scrollState: ScrollState, navController: NavController, storeManager : StoreManager) {

    val featuredApps : AppData = storeManager.featuredApps.value
    val allApps : AppData = storeManager.appList.value

    Scaffold(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(it)
                .verticalScroll(scrollState)
        ) {

            //Search bar row
            Row(
                Modifier.padding(15.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp)
                ) {
                    SearchBar()
                }

                Card(shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .size(50.dp)
                        .padding(0.dp),
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
                        AppListRowHeader(heading = "Developer's Choice",
                            modifier = Modifier.padding(top = 20.dp))
                        LazyRow {
                            items (featuredApps.apps.size){
                                    index ->
                                Column {
                                    DevChoiceAppTemplate(navController = navController,
                                        appData = featuredApps.apps[index])
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
        Card(shape = RoundedCornerShape(20.dp),
        ) {
            CoilImage(imageModel = appData.appGraphic,
                contentDescription = "",
                contentScale = ContentScale.Crop)
        }
    }

    Column(Modifier.padding(start = 12.dp)) {
        Text(text = appData.appName!!,
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
        )
        Row(Modifier.padding(start = 0.dp)) {

            Text(text = "${appData.size} MB",
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
            )
            Icon(imageVector = Icons.Filled.Bolt,
                contentDescription = "stars",
                tint = colorResource(id = R.color.star_color),
                modifier = Modifier.size(16.dp))

            Text(text = "${appData.complexity}",
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
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


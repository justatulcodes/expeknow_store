package com.expeknow.store.ui.windows

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.rounded.Apps
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.expeknow.store.R
import com.expeknow.store.network.App
import com.expeknow.store.network.StoreManager
import com.expeknow.store.widgets.small.SearchBar
import com.skydoves.landscapist.coil.CoilImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultsPage(navController: NavController, storeManager: StoreManager) {

    val scrollState = rememberScrollState()
    val searchedApps = storeManager.searchedAppsList.value


    Scaffold (Modifier.fillMaxSize()) {
        Column(
            Modifier
                .verticalScroll(scrollState)
                .padding(10.dp)
                .padding(it)) {

            //search bar
            Row (modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)){
                IconButton(onClick = {navController.popBackStack()}) {
                    Icon(imageVector = Icons.Rounded.ArrowBackIos, "",
                        Modifier.size(20.dp))
                }
                Card(shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    border = BorderStroke(
                        1.dp, color = MaterialTheme.colorScheme.outlineVariant
                    ),) {
                    SearchBar(storeManager, navController)
                }
            }

            Column(Modifier.fillMaxSize()) {
                if(searchedApps.isEmpty()){
                    Column(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                        Icon(imageVector = Icons.Rounded.Apps, contentDescription = "apps",
                        Modifier.size(35.dp))
                        Text(text = "Search listed apps...",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(top = 4.dp))
                    }
                }
                else {
                    Text(
                        text = "${searchedApps.size} results found...",
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(10.dp)
                    )

                    SearchResultAppItem(appList = searchedApps, navController = navController)
                }

                Divider(
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(10.dp)
                )

                val suggestedApps = ArrayList<App>()
                val allApps = storeManager.appList.value
                if(searchedApps.isNotEmpty()){
                    for(app in allApps.apps!!){
                        if(!searchedApps.contains(app)){
                            suggestedApps.add(app)
                        }
                    }
                }
                if(suggestedApps.isNotEmpty()){
                    Text(text = "Or... try something new!",
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(10.dp))
                    SearchResultAppItem(appList = suggestedApps, navController = navController)
                }
                else if(suggestedApps.isEmpty() && searchedApps.isEmpty()){
                    Text(text = "Or... try something new!",
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(10.dp))
                    SearchResultAppItem(appList = allApps.apps!!, navController = navController)
                }
                else{
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(imageVector = Icons.Filled.Bolt, contentDescription = "expeknow logo",
                            modifier = Modifier
                                .size(50.dp)
                                .padding(5.dp), tint = colorResource(id = R.color.bolt_color))
                        Text(text = "That's all we have for today :)",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }

    }
    }
}

@Composable
fun SearchResultAppItem(appList : List<App>, navController: NavController) {
    repeat(appList.size){index ->
        Row(modifier = Modifier
            .padding(vertical = 5.dp)
            .clickable {
                navController.navigate("details/${appList[index].appId}")
            }) {
            Card(shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .size(80.dp)
                    .padding(10.dp)) {
                CoilImage(imageModel = appList[index].icon,
                    contentDescription = "app icon",
                )
            }
            Column {
                Text(text = appList[index].appName!!,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold)
                Text(text = appList[index].tagLine!!,
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                )
                Row {
                    Text(
                        text = "${appList[index].size} MB |",
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Icon(
                        imageVector = Icons.Filled.Bolt,
                        contentDescription = "stars",
                        tint = colorResource(id = R.color.bolt_color),
                        modifier = Modifier.size(20.dp)
                    )

                    Text(
                        text = "${appList[index].complexity}",
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }

    }
}

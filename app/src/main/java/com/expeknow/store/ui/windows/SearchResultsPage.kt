package com.expeknow.store.ui.windows

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun SearchResultsPage(navController: NavController, storeManager: StoreManager) {

    val scrollState = rememberScrollState()
    val searchedApps = storeManager.searchedAppsList.value


    Scaffold {
        Column(
            Modifier
                .verticalScroll(scrollState)
                .padding(10.dp)
                .padding(it)) {

            //search bar
            Row (modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)){
                IconButton(onClick = {navController.popBackStack()}) {
                    Icon(imageVector = Icons.Rounded.ArrowBackIos, "")
                }
                Card(shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp),
                    elevation = 3.dp) {
                    SearchBar(storeManager, navController)
                }
            }

            if(searchedApps.isEmpty()){
                Text(text = "Search any app...",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold)
            }
            else{
                Text(text = "${searchedApps.size} results found...",
                    fontStyle = FontStyle.Italic,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(10.dp))

                SearchResultAppItem(appList = searchedApps, navController = navController)

                Divider(
                    thickness = 3.dp,
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
                    Text(text = "Wanna try something new?",
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(10.dp))
                    SearchResultAppItem(appList = suggestedApps, navController = navController)
                }else{
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(imageVector = Icons.Filled.Bolt, contentDescription = "expeknow logo",
                            modifier = Modifier
                                .size(60.dp)
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
                elevation = 5.dp,
                modifier = Modifier
                    .size(80.dp)
                    .padding(10.dp)) {
                CoilImage(imageModel = appList[index].icon,
                    contentDescription = "app icon",
                )
            }
            Column(modifier = Modifier.padding(end = 5.dp)) {
                Text(text = appList[index].appName!!,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold)
                Text(text = appList[index].tagLine!!,
                    fontSize = 12.sp,)
                Row {
                    androidx.compose.material3.Text(
                        text = "${appList[index].size} MB |",
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                    )
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Filled.Bolt,
                        contentDescription = "stars",
                        tint = colorResource(id = R.color.bolt_color),
                        modifier = Modifier.size(20.dp)
                    )

                    androidx.compose.material3.Text(
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

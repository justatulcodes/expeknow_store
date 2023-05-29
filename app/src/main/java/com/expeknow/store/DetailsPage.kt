package com.expeknow.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.ButtonElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@ExperimentalMaterial3Api
@Composable
fun DetailsPage(modifier : Modifier = Modifier) {

    val app_images = ArrayList<Int>()
    app_images.add(R.drawable.home_window)
    app_images.add(R.drawable.details_window)
    app_images.add(R.drawable.image_window)
    app_images.add(R.drawable.search_window)
    app_images.add(R.drawable.saved_window)
    val scrollState = rememberScrollState()

    Scaffold(topBar = { TopBar()},
    ) {
        Column(modifier = modifier
            .padding(it)
            .padding(5.dp)
            .verticalScroll(scrollState)) {
            //App logo, name, publisher and tags
            Row {
                    Card(modifier = modifier
                        .size(120.dp)
                        .padding(10.dp),
                    shape = RoundedCornerShape(20.dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.icon),
                            contentDescription = "app logo")
                    }
                    Box(modifier = modifier.align(CenterVertically)){
                        Column {
                            Text(text = "Life Calender",
                                fontSize = 22.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.sp,
                                modifier = modifier.padding(start = 10.dp, bottom = 5.dp)
                            )
                            Text(text = "By Epeknow",
                                fontSize = 14.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 0.sp,
                                color = Color.LightGray,
                                modifier = modifier.padding(start = 10.dp, bottom = 10.dp)
                            )
                            Row(modifier = modifier.padding(start = 10.dp)) {
                                Card(
                                    modifier = modifier.padding(0.dp),
                                    shape = RoundedCornerShape(5.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.LightGray,
                                        contentColor = Color.DarkGray
                                    )
                                ) {
                                    Text(text = "Productivity",
                                        fontSize = 10.sp,
                                        modifier = modifier.padding(3.dp))
                                }

                                Card(modifier = modifier.padding(start = 5.dp),
                                    shape = RoundedCornerShape(5.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.LightGray,
                                        contentColor = Color.DarkGray
                                    )
                                ) {
                                    Text(text = "Personalization", fontSize = 10.sp,
                                        modifier = modifier.padding(3.dp))
                                }
                            }
                        }
                    }

                }
            //app downloads, reviews and size
            Row(horizontalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)) {
                Box(){
                    Text(text = "12 MB",
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.sp,
                        color = Color.LightGray,
                        modifier = modifier
                            .padding(start = 10.dp, bottom = 10.dp)
                    )
                }
                Box {
                    Text(text = "12K Downloads",
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.sp,
                        color = Color.LightGray,
                        modifier = modifier
                            .padding(start = 10.dp, bottom = 10.dp)

                    )
                }

                Box {
                    Text(text = "40 Reviews",
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.sp,
                        color = Color.LightGray,
                        modifier = modifier
                            .padding(start = 10.dp, bottom = 10.dp)

                    )
                }

            }

            //Download button
            Button(onClick = { },
            modifier = modifier
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
                    modifier = modifier.padding(3.dp)
                )
            }

            //Video card
            Box(modifier = modifier.padding(10.dp)) {
                Card(shape = RoundedCornerShape(20.dp),
                    modifier = modifier
                        .fillMaxWidth()
                        .height(200.dp),
                colors = CardDefaults.cardColors(
                    contentColor = Color.Gray
                )) {
                    Icon(imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "play video",
                        modifier = modifier.padding(20.dp))
                }
            }

            //Decription of the app
            Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiut enim ad minim veniam... Read More. ",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = modifier.padding(20.dp))

            //App screenshots
            LazyRow(modifier = modifier.padding(0.dp)) {
                items(app_images.size){
                    val index = it
                    Card(
                        modifier
                            .padding(5.dp)
                            .height(333.dp)
                            .width(150.dp),
                    shape = RoundedCornerShape(10.dp)
                    ) {
                        Image(painter = painterResource(id = app_images[index])
                            , contentDescription = "", contentScale = ContentScale.Crop)
                    }
                }
            }

            //other picks
            Row(
                Modifier
                    .padding(vertical = 0.dp)
            ) {
                Column() {

                    Text(text = "Other Apps",
                        fontSize = 25.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp,
                        modifier = modifier.padding(top = 20.dp, start = 10.dp, bottom = 6.dp)
                    )
                    LazyRow() {
                        val list = Apps()
                        items (list.size){
                                index ->
                            Column(Modifier.padding(6.dp)) {
                                Box(
                                    modifier
                                        .width(100.dp)
                                        .height(100.dp)) {
                                    Card(shape = RoundedCornerShape(20.dp),
                                        elevation = CardDefaults.cardElevation(5.dp)) {
                                        Image(painter = painterResource(id = list[index].appLogo),
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop
                                        )
                                    }

                                }
                                Text(text = list[index].appName,
                                    textAlign = TextAlign.Center,
                                    modifier = modifier.padding(6.dp),
                                    fontSize = 14.sp
                                )

                            }


                        }
                    }

                }
            }

            //End credits

            Box(modifier = modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = "")},
        navigationIcon = {
            Icon(imageVector = Icons.Outlined.ArrowBackIos,
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .padding(start = 8.dp)
                )
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "more options"
                )
            }
        }

    ) 
}
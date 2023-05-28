package com.expeknow.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@ExperimentalMaterial3Api
@Composable
fun DetailsPage(modifier : Modifier = Modifier) {


    Scaffold(topBar = { TopBar()} ) {
        Column(modifier = modifier
            .padding(it)
            .padding(5.dp)) {
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
                    modifier = modifier.fillMaxWidth().height(200.dp),
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
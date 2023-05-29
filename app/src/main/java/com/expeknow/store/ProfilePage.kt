package com.expeknow.store

import android.annotation.SuppressLint
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.Mail
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.material.icons.rounded.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun profilePage(modifier : Modifier = Modifier) {
    Scaffold() {
        Column(
            Modifier
                .padding(it)) {

            //profile Image
            Card(modifier = modifier
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .size(150.dp),
            shape = RoundedCornerShape(30.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.profile),
                    contentDescription = "",
                contentScale = ContentScale.FillBounds)
            }

            //Name
            Text(text = "Atul Kumar",
            modifier = modifier.padding(horizontal = 20.dp),
            fontSize = 50.sp,
            fontWeight = FontWeight.Black)

            //description
            Text(text = "A self taught programmer trying to be an Android Developer.",
            modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            fontSize = 16.sp,
            color = Color.LightGray)
        
            
            //Social links
            Row (Modifier.padding(horizontal = 10.dp)){
                Card(shape = RoundedCornerShape(20.dp),
                    modifier = modifier.padding(horizontal = 10.dp, vertical = 20.dp)) {
                    Icon(imageVector = Icons.Rounded.Mail, contentDescription = "mail",
                    modifier = modifier
                        .size(50.dp)
                        .padding(10.dp))
                }
                Card(shape = RoundedCornerShape(20.dp),
                    modifier = modifier.padding(horizontal = 10.dp, vertical = 20.dp)) {
                    Icon(imageVector = Icons.Rounded.Code, contentDescription = "Github",
                    modifier = modifier
                        .size(50.dp)
                        .padding(10.dp))
                }
                Card(shape = RoundedCornerShape(20.dp),
                    modifier = modifier.padding(horizontal = 10.dp, vertical = 20.dp)) {
                    Icon(imageVector = Icons.Rounded.PhotoCamera, contentDescription = "instagram",
                    modifier = modifier
                        .size(50.dp)
                        .padding(10.dp))
                }
                Card(shape = RoundedCornerShape(20.dp),
                    modifier = modifier.padding(horizontal = 10.dp, vertical = 20.dp)) {
                    Icon(imageVector = Icons.Rounded.Work, contentDescription = "LinkedIn",
                    modifier = modifier
                        .size(50.dp)
                        .padding(10.dp))
                }
            }



        }
    }
}


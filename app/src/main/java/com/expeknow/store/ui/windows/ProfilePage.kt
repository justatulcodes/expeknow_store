package com.expeknow.store.ui.windows

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.Mail
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.material.icons.rounded.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.expeknow.store.R
import com.expeknow.store.widgets.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(navController: NavController) {
    Scaffold(
        topBar = {
            TopBar(navController)
        }
    ) {
        Column(
            Modifier
                .padding(it)) {

            //profile Image
            Card(modifier = Modifier
                .padding(vertical = 30.dp, horizontal = 20.dp)
                .size(150.dp),
            shape = RoundedCornerShape(30.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.profile),
                    contentDescription = "",
                contentScale = ContentScale.FillBounds)
            }

            //Name
            Text(text = "Atul Kumar",
            modifier = Modifier.padding(horizontal = 20.dp),
            fontSize = 50.sp,
            fontWeight = FontWeight.Black)

            //description
            Text(text = "A self taught programmer trying to be an Android Developer.",
                Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            fontSize = 16.sp,
            color = Color.Gray)

            //Social links
            Row (Modifier.padding(horizontal = 10.dp)){
                SocialIcons(icon = Icons.Rounded.Mail, contentDescription = "mail")
                SocialIcons(icon = Icons.Rounded.Code, contentDescription = "github")
                SocialIcons(icon = Icons.Rounded.PhotoCamera, contentDescription = "instagram")
                SocialIcons(icon = Icons.Rounded.Work, contentDescription = "linkedin")
            }
        }
    }
}


@Composable
fun SocialIcons(icon: ImageVector, contentDescription: String) {
    Card(shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)) {
        Icon(imageVector = icon, contentDescription = contentDescription,
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp))
    }
}




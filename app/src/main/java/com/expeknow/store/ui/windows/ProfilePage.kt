package com.expeknow.store.ui.windows

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.Mail
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.material.icons.rounded.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.expeknow.store.Constants
import com.expeknow.store.R
import com.expeknow.store.widgets.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(navController: NavController, scrollState: ScrollState) {
    Scaffold(
        topBar = {
            TopBar(navController)
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .verticalScroll(scrollState)) {

            //profile Image
            Card(modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .size(150.dp),
            shape = RoundedCornerShape(100.dp)
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
            Text(text = "A self-taught 21-year-old programmer striving to be an Android Developer." +
                    " I learn, Unlearn and Re-learn.",
                Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            fontSize = 16.sp,
            color = Color.Gray)

            //Social links
            Row (
                Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly){
                SocialIcons(icon = Icons.Rounded.Call, contentDescription = "call")
                SocialIcons(icon = Icons.Rounded.Mail, contentDescription = "mail")
                SocialIcons(icon = Icons.Rounded.Code, contentDescription = "github")
                SocialIcons(icon = Icons.Rounded.PhotoCamera, contentDescription = "instagram")
                SocialIcons(icon = Icons.Rounded.Work, contentDescription = "linkedin")
            }

            ProfileDetailHeading(headingString = "Core Skills", description = Constants().loremIpsum)
            ProfileDetailHeading(headingString = "Technical Skills", description = Constants().loremIpsum)
            ProfileDetailHeading(headingString = "Extra Skills", description = Constants().loremIpsum)
            ProfileDetailHeading(headingString = "Education", description = Constants().loremIpsum)


        }
    }
}


@Composable
fun SocialIcons(icon: ImageVector, contentDescription: String) {
    Card(shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(horizontal = 0.dp, vertical = 20.dp)) {
        Icon(imageVector = icon, contentDescription = contentDescription,
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp))
    }
}


@Composable
fun ProfileDetailHeading(headingString: String, description: String) {

    val interactionSource = remember{
        MutableInteractionSource()
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = headingString,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .clickable(interactionSource = interactionSource, indication = null) {
                        isExpanded = !isExpanded
                    },
                fontSize = 35.sp,
                fontWeight = FontWeight.Black)
            Icon(imageVector =  if(isExpanded) Icons.Filled.ArrowDropDown else Icons.Filled.ArrowRight,
                contentDescription = "expand",
                modifier = Modifier
                    .clickable { isExpanded = !isExpanded })
        }
        AnimatedVisibility(visible = isExpanded,
        ) {
            Text(text = "\n"+ description,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 20.dp),
            )
        }
    }

    Divider(modifier = Modifier.padding(vertical = 20.dp, horizontal = 30.dp))

}



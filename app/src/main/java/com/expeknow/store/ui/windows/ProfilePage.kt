package com.expeknow.store.ui.windows

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
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
            Surface (shadowElevation = 3.dp){
                TopBar(navController)

            }
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
            style = MaterialTheme.typography.displayLarge)

            //description
            Text(text = "A self-taught 21-year-old programmer striving to be an Android Developer." +
                    " I learn, Unlearn and Re-learn.",
                Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            fontSize = 15.sp,
            )

            //Social links
            Row (
                Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly){
                SocialIcons(id = R.drawable.download, contentDescription = "download",
                    link = "https://firebasestorage.googleapis.com/v0/b/expeknow-store.appspot.com/o/Miscellaneous%2FCV%20Atul.pdf?alt=media&token=d025f0cc-c006-4405-83c9-687ad8eacd94")
                SocialIcons(id = R.drawable.linkedin, contentDescription = "linkedin",
                    link = "https://www.linkedin.com/in/expeknow/")
                SocialIcons(id = R.drawable.github, contentDescription = "github",
                    link = "https://github.com/expeknow")
                SocialIcons(id = R.drawable.instagram, contentDescription = "instagram",
                    link = "https://www.instagram.com/expeknow/")
                SocialIcons(id = R.drawable.youtube, contentDescription = "youtube",
                    link = "https://www.youtube.com/channel/UCCWs3eb5wvVfJ8JGWBvQdxw")
            }

            ProfileDetailHeading(headingString = "Core Skills", description = Constants().loremIpsum)
            ProfileDetailHeading(headingString = "Technical Skills", description = Constants().loremIpsum)
            ProfileDetailHeading(headingString = "Extra Skills", description = Constants().loremIpsum)
            ProfileDetailHeading(headingString = "Education", description = Constants().loremIpsum)


        }
    }
}


@Composable
fun SocialIcons(id : Int, contentDescription: String, link: String) {

    val uriHandler = LocalUriHandler.current
    Card(shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(horizontal = 0.dp, vertical = 20.dp)) {
        IconButton(onClick = {
                uriHandler.openUri(link)
        }) {
            Icon(
                painterResource(id = id) , contentDescription = contentDescription,
                modifier = Modifier
                    .size(60.dp)
                    .padding(5.dp))
        }

    }
}


@Composable
fun ProfileDetailHeading(headingString: String, description: String) {

    val interactionSource = remember{
        MutableInteractionSource()
    }
    var isExpanded by remember {
        mutableStateOf(true)
    }
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = headingString,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .clickable(interactionSource = interactionSource, indication = null) {
                        isExpanded = !isExpanded
                    },
                style = MaterialTheme.typography.displayMedium)
            Icon(imageVector =  if(isExpanded) Icons.Filled.ArrowDropDown else Icons.Filled.ArrowRight,
                contentDescription = "expand",
                modifier = Modifier
                    .clickable { isExpanded = !isExpanded })
        }
        AnimatedVisibility(visible = isExpanded,
        ) {
            Text(text = "\n"+ description,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 20.dp),
            )
        }
    }

    Divider(modifier = Modifier.padding(vertical = 20.dp, horizontal = 0.dp))

}



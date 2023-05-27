import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.expeknow.store.Apps
import com.expeknow.store.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Store(modifier: Modifier = Modifier, scrollState: ScrollState) {
    Scaffold(modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(it)
                .verticalScroll(scrollState)
        ) {
            
            //Search bar row
            Row(
                modifier.padding(10.dp)
            ) {
                Card(shape = RoundedCornerShape(50.dp),
                    modifier = modifier
                        .weight(1f)
                        .padding(end = 10.dp)
                ) {
                    SearchBar()
                }

                Card(shape = RoundedCornerShape(50.dp),
                    modifier = modifier.size(50.dp)
                        .padding(0.dp)) {
                    Image(painter = painterResource(id = R.drawable.profile_pic),
                        contentDescription = "profile pic",
                       )
                }

            }


            //Developer's Choice
            Row(
                Modifier
                    .padding(top = 20.dp)
            ) {
                Column() {
                    Text(text = "Developer's Choice",
                        fontSize = 25.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.sp,
                        modifier = modifier.padding(start = 10.dp, bottom = 5.dp)
                    )
                    LazyRow() {
                        val list = Apps()
                        items (list.size){
                                index ->
                            Column() {
                                Box(
                                    modifier
                                        .width(280.dp)
                                        .height(180.dp)
                                        .padding(4.dp)) {
                                    Card(shape = RoundedCornerShape(20.dp),
                                    ) {
                                        Image(painter = painterResource(id = list[index].graphic),
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }

                                Column(Modifier.padding(start = 12.dp)) {
                                    Text(text = list[index].appName,
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                    Row(Modifier.padding(start = 0.dp)) {

                                        Text(text = "${list[index].size} MB",
                                            fontSize = 12.sp,
                                            fontFamily = FontFamily.SansSerif,
                                            fontWeight = FontWeight.SemiBold,
//                                        modifier = modifier.weight(1f),
                                        )
                                        Icon(imageVector = Icons.Filled.Star,
                                            contentDescription = "",
                                            tint = colorResource(id = R.color.star_color),
                                            modifier = Modifier.size(16.dp))

                                        Text(text = "${list[index].stars}",
                                            fontSize = 12.sp,
                                            fontFamily = FontFamily.SansSerif,
                                            fontWeight = FontWeight.SemiBold,
                                        )
                                    }
                                }



                            }



                        }
                    }

                }
            }

            //Newly Released Apps Section
            Row(
                Modifier
                    .padding(vertical = 0.dp)
            ) {
                Column() {

                    Text(text = "Newly Released Apps",
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

            //Old Apps
            Row(
                Modifier
                    .padding(vertical = 0.dp)
            ) {
                Column() {

                    Text(text = "Old Apps",
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

            //Under Development
            Row(
                Modifier
                    .padding(vertical = 0.dp)
            ) {
                Column() {

                    Text(text = "Under Development",
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


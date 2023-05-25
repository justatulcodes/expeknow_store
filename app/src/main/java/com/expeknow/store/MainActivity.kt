package com.expeknow.store

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.expeknow.store.ui.theme.ExpeknowStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpeknowStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Store()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Store(modifier: Modifier = Modifier) {
    Scaffold(modifier.fillMaxSize()) {
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(5.dp)) {
                Card(shape = RoundedCornerShape(50.dp),
                modifier = modifier
                    .weight(2f)
                    .padding(10.dp)) {
                    SearchBar()
                }
                IconButton(onClick = { /*TODO*/ },
                modifier = modifier.padding(vertical = 10.dp)) {
                    Icon(imageVector = Icons.Outlined.Person,
                        contentDescription = "user")
                }

            }

        Row(Modifier.fillMaxWidth()) {
                    LazyRow() {
                        item {
                            Box(modifier.size(200.dp)) {
                                Card(shape = RoundedCornerShape(20.dp)) {
                                    Image(painter = painterResource(id = R.drawable.ap), contentDescription = "")
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
            IconButton(
                onClick = {
                }
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 15.dp, end = 5.dp)
                        .size(24.dp)
                )
            }

        },
        trailingIcon = {
            if (inputText != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        inputText =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = 15.dp, end = 5.dp)
                            .size(24.dp)
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


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExpeknowStoreTheme {
        Greeting("Android")
    }
}
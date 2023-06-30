package com.expeknow.store.widgets.small

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.expeknow.store.NavigationScreens
import com.expeknow.store.network.App
import com.expeknow.store.network.StoreManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(storeManager: StoreManager, navController: NavController) {
    var inputText by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var isEnabled by remember {
        mutableStateOf(false)
    }

    val appList : ArrayList<App> = ArrayList()
    if (storeManager.appList.value.apps != null){
        isEnabled = true
        appList.addAll(storeManager.appList.value.apps!!)
    }

    TextField(
        value = inputText,
        enabled = isEnabled,
        keyboardActions = KeyboardActions(
            onDone = {
                storeManager.getSearchApps(inputText.text)
            }
        ),
        onValueChange = { value ->
            inputText = value
        },
        textStyle = TextStyle(fontSize = 18.sp),
        leadingIcon = {
            IconButton(
                onClick = {
                    val searchKey = inputText.text
                    storeManager.getSearchApps(searchKey)
                }
            ){
                Icon(
                    Icons.Default.Search,
                    contentDescription = "search",
                    modifier = Modifier
                        .padding(start = 10.dp, end = 5.dp)
                        .size(35.dp)
                )
            }

        },
        placeholder = { Text(text = "Search apps...", fontSize = 16.sp, color = Color.LightGray)},
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
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
    )
}
package com.expeknow.store.widgets.small

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
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
        textStyle = TextStyle(fontSize = 16.sp),
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
                        .size(30.dp)
                        .padding(start = 5.dp)
                )
            }

        },
        placeholder = { Text(text = "Search apps...", fontSize = 16.sp)},
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
                            .size(25.dp)
                    )
                }
            }
        },
        singleLine = true,

    )
}
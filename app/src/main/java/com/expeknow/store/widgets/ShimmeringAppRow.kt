package com.expeknow.store.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import com.expeknow.store.ui.utilities.shimmerEffect

@Composable
fun ShimmeringAppRow() {

    Column() {
        Box(
            modifier = Modifier
                .height(40.dp)
                .width(200.dp)
                .padding(6.dp)
                .shimmerEffect()
        )
        Row() {
            repeat(5){
                AppRowItem()
            }
        }
    }

}

@Composable
fun AppRowItem() {

    Column(modifier = Modifier.padding(horizontal = 6.dp, vertical = 15.dp)) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .width(60.dp)
                .height(25.dp)
                .padding(6.dp)
                .shimmerEffect()
        )
    }

}

package com.expeknow.store.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.expeknow.store.ui.utilities.shimmerEffect

@Composable
fun ShimmeringFeaturedAppRow() {
        Column(modifier = Modifier.padding(top = 20.dp, bottom = 15.dp)) {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .width(250.dp)
                    .padding(6.dp)
                    .shimmerEffect()
            )
            Row {
                repeat(1) {
                    FeaturedAppRowItem()
                }
            }
        }
}

@Composable
fun FeaturedAppRowItem(modifier: Modifier = Modifier) {
    Column() {
        Box(
            modifier = modifier
                .width(350.dp)
                .height(180.dp)
                .padding(6.dp)
                .shimmerEffect()
        )
        Box(
            modifier = modifier
                .width(150.dp)
                .height(25.dp)
                .padding(6.dp)
                .shimmerEffect()
        )
        Box(
            modifier = modifier
                .width(100.dp)
                .height(25.dp)
                .padding(6.dp)
                .shimmerEffect()
        )
    }

}


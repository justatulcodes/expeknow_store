package com.expeknow.store.widgets

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.expeknow.store.Constants
import com.expeknow.store.ui.utilities.shimmerEffect

@Composable
fun ShimmeringFeaturedAppRow() {
        Column(modifier = Modifier.padding(vertical = 15.dp)) {
            Box(
                modifier = Modifier
                    .height(45.dp)
                    .width(220.dp)
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


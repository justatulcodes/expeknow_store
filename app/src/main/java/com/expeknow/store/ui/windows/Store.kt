package com.expeknow.store.ui.windows

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.expeknow.store.network.StoreManager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Store(scrollState: ScrollState, navController: NavController, storeManager : StoreManager) {

    var currentSelectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val tabTitles = listOf("Android Apps", "UI Builds", "Python GUI")
    var pagerState = rememberPagerState()
    var isTextBig by remember { mutableStateOf(false) }

    // to modify the width of individual tab bar header
    Class
        .forName("androidx.compose.material3.TabRowKt")
        .getDeclaredField("ScrollableTabRowMinimumTabWidth").apply {
            isAccessible = true
        }.set(LocalContext.current, 0f)


    Column(Modifier.fillMaxWidth()) {
        
        Spacer(modifier = Modifier.height(5.dp))
        ScrollableTabRow(selectedTabIndex = currentSelectedTabIndex,
            divider =  {},
            backgroundColor = Color.Transparent,
            edgePadding = 5.dp,
            indicator = {}
        ) {
            tabTitles.forEachIndexed { currentIndex, title ->
                val isSelected = currentSelectedTabIndex == currentIndex
                val textColor = if (isSelected) Color.Black else Color.LightGray
                Tab(selected = isSelected,
                    onClick = {
                        currentSelectedTabIndex = currentIndex
                    },
                    text = {
                        isTextBig = currentSelectedTabIndex == currentIndex
                        val textSize = animateDpAsState(
                            targetValue = if (isTextBig) 22.dp else 16.dp,
                            animationSpec = tween(durationMillis = 100), label = ""
                        ).value

                        val textStyle = TextStyle(
                            fontSize = with(LocalDensity.current) { textSize.toSp() },
                        )

                        Text(
                            text = title,
                            style = textStyle
                        )
                    },
                    unselectedContentColor = textColor,
                    selectedContentColor = textColor
                )
            }
        }
        HorizontalPager( // 4.
            count = tabTitles.size,
            state = pagerState,
        ) { index -> run {
            when (index) {
                0 -> appsPage(
                    scrollState = scrollState,
                    navController = navController,
                    storeManager = storeManager
                )
                1 -> ProfilePage(navController = navController, scrollState = scrollState)
                else -> ProfilePage(navController = navController, scrollState = scrollState)
            }
        }

        }
        // Update the tab index when pagerState.currentPage changes
        LaunchedEffect(pagerState.currentPage) {
            currentSelectedTabIndex = pagerState.currentPage
        }
        // Coroutine scope for scrolling animation
        LaunchedEffect(currentSelectedTabIndex) {
            // Only scroll if the selected tab index is different from the current page
            if (pagerState.currentPage != currentSelectedTabIndex) {
                pagerState.animateScrollToPage(currentSelectedTabIndex)
            }
        }
    }

}

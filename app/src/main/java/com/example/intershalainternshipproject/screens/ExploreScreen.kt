package com.example.intershalainternshipproject.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.intershalainternshipproject.screens.explorescreencomponents.BusinessView
import com.example.intershalainternshipproject.screens.explorescreencomponents.PersonalView
import com.example.intershalainternshipproject.screens.explorescreencomponents.ServiceView

val tabItems = listOf("Personal", "Services", "Business")


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreScreenView() {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val pageState = rememberPagerState {
        tabItems.size
    }
    LaunchedEffect(selectedTabIndex) {
        pageState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pageState.currentPage) {
        selectedTabIndex = pageState.currentPage
    }
    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color(0, 67, 117, 225),
            contentColor = Color.White
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = { Text(text = item) }
                )
            }
        }
        HorizontalPager(state = pageState, modifier = Modifier.fillMaxWidth()) { index ->
            when (index) {
                0 -> PersonalView()
                1 -> ServiceView()
                else -> BusinessView()
            }
        }
    }
}
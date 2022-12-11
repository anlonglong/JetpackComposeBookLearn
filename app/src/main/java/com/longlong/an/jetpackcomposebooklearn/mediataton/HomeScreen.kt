package com.longlong.an.jetpackcomposebooklearn.mediataton

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.longlong.an.jetpackcomposebooklearn.R
import com.longlong.an.jetpackcomposebooklearn.ui.theme.*

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ){
        Column {
            Greeting()
            ChipSection(
                listOf(
                    "Sweet sleep",
                    "Insomnia",
                    "Depression,",
                    "Sweet sleep",
                    "Insomnia",
                    "Depression,",
                    "Sweet sleep",
                    "Insomnia",
                    "Depression,"
                )
            )
            CurrentMeditation()
            FeatureSection(
                features = listOf(
                    Feature(
                        title = "Sleep meditation",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    Feature(
                        title = "Tips for sleeping",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                    Feature(
                        title = "Night island",
                        R.drawable.ic_headphone,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ),
                    Feature(
                        title = "Calming sounds",
                        R.drawable.ic_headphone,
                        Beige1,
                        Beige2,
                        Beige3
                    )
                )
            )
        }
        BottomMenu(
            items = listOf(
                BottomMenuContent("Home", R.drawable.ic_home),
                BottomMenuContent("Meditate", R.drawable.ic_bubble),
                BottomMenuContent("Sleep", R.drawable.ic_moon),
                BottomMenuContent("Music", R.drawable.ic_music),
                BottomMenuContent("Profile", R.drawable.ic_profile),
            ), modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeatureSection(features: List<Feature>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Featured",
            color = Color.White,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            this.items(features.size) {
                FeatureSectionItem(features[it])
            }
        }
    }

}

@Composable
fun FeatureSectionItem(feature: Feature) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(horizontal = 7.5.dp, vertical = 7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(15.dp))
            .background(feature.darkColor)
    ) {
        Text(
            text = feature.title,
            style = MaterialTheme.typography.h6,
            lineHeight = 26.sp,
            modifier = Modifier
                .padding(start = 15.dp, top = 15.dp)
                .align(Alignment.TopStart),
            color = Color.White,
        )
        Icon(
            painter = painterResource(id = feature.iconId),
            contentDescription = feature.title,
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 15.dp, bottom = 15.dp)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(end = 15.dp, bottom = 15.dp)
                .clip(RoundedCornerShape(15.dp))
                .align(Alignment.BottomEnd)
                .background(ButtonBlue)
                .padding(vertical = 6.dp, horizontal = 15.dp)
        ) {
            Text(
                text = "Start",
                color = TextWhite,
                modifier = Modifier.clickable {
                }
            )
        }
    }
}

@Composable
fun CurrentMeditation() {
    Box(
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(LightRed)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column() {
                Text(text = "Daily Thought", color = TextWhite, style = MaterialTheme.typography.h4)
                Text(
                    text = "Meditation 3-10 min",
                    color = TextWhite,
                    style = MaterialTheme.typography.body2
                )
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(ButtonBlue)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun Greeting() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Good morning ,An",
                color = Color.White,
                style = MaterialTheme.typography.h4
            )
            Text(
                text = "We wish you have a good day",
                color = Color.White,
                style = MaterialTheme.typography.body2
            )
        }
        Icon(
            painter = painterResource(
                id = R.drawable.ic_baseline_search_24,
            ),
            tint = Color.White, contentDescription = "Search",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChipSection(list: List<String>) {
    var selectedChipIndex by remember { mutableStateOf(0) }
    LazyRow() {
        this.items(list.size) {
            ChipSectionItem(list.size, list[it], it, selectedChipIndex) {
                selectedChipIndex = it
            }
        }
    }
}

@Composable
fun ChipSectionItem(
    count: Int,
    s: String,
    index: Int,
    selectedChipIndex: Int,
    onClick: () -> Unit
) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(
                start = 15.dp,
                top = 15.dp,
                bottom = 15.dp,
                end = if (count - 1 == index) 15.dp else 0.dp
            )
            .clip(
                RoundedCornerShape(10.dp)
            )
            .background(
                if (selectedChipIndex == index) ButtonBlue
                else DarkerButtonBlue
            )
            .padding(15.dp)
            .clickable {
                onClick()
            }
    ) {
        Text(text = s, color = TextWhite)
    }
}

@Composable
@Preview(showBackground = true)
fun HeadPreview() {
    HomeScreen()
}
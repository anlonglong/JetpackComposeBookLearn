package com.longlong.an.jetpackcomposebooklearn.mediataton

import androidx.annotation.DrawableRes
import com.longlong.an.jetpackcomposebooklearn.R

data class BottomMenuContent(
    val title: String,
    @DrawableRes val iconId: Int
)

val menues = listOf(
BottomMenuContent("Home", R.drawable.ic_home),
BottomMenuContent("Meditate", R.drawable.ic_bubble),
BottomMenuContent("Sleep", R.drawable.ic_moon),
BottomMenuContent("Music", R.drawable.ic_music),
BottomMenuContent("Profile", R.drawable.ic_profile),
)

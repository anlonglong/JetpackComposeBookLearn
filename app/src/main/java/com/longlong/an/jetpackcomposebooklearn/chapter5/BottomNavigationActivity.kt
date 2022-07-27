package com.longlong.an.jetpackcomposebooklearn.chapter5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.longlong.an.jetpackcomposebooklearn.R

class BottomNavigationActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BottomNavigationView()
        }
    }

    @Composable
    fun One() {
        BaseDefault(content = "One", Color.Red)
    }

    @Composable
    fun Two() {
        BaseDefault(content = "Two",Color.Magenta)
    }

    @Composable
    fun Three() {
        BaseDefault(content = "Three",Color.Blue)
    }


    @Composable
    fun Four() {
        BaseDefault(content = "Four",Color.Yellow)
    }

    @Composable
    fun BaseDefault(content: String, bg: Color) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(bg),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = content, fontSize = 50.sp)
        }
    }

    @Composable
    fun BottomNavigationView() {
        val tabs = Tab.values()
        var position by remember { mutableStateOf(Tab.ONE) }
        Scaffold(bottomBar = {
            BottomNavigation {
                tabs.forEach { tab ->
                    BottomNavigationItem(modifier = Modifier.background(Color.Gray),selected = position == tab, onClick = {
                        position = tab
                    }, icon = {
                        Icon(painter = painterResource(id = tab.res), contentDescription = "")
                    },
                        label = { Text(text = tab.title) },
                        alwaysShowLabel = true,
                        selectedContentColor = Color.Red,
                        unselectedContentColor = Color.Cyan
                    )
                }
            }
        }) {
            when (position) {
                Tab.ONE -> One()
                Tab.TWO -> Two()
                Tab.THREE -> Three()
                Tab.FOUR -> Four()
            }
        }
    }
}

enum class Tab(val title: String, val res: Int) {
    ONE("One", R.drawable.ic_baseline_assessment_24),
    TWO("two", R.drawable.ic_baseline_assignment_ind_24),
    THREE("Three", R.drawable.ic_baseline_audiotrack_24),
    FOUR("four", R.drawable.ic_baseline_attach_money_24),
}
package com.longlong.an.jetpackcomposebooklearn.chapter4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ColumnActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColunmTest1()
        }
    }


    @Composable
    fun ColunmTest() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "One")
            Text(text = "Two", modifier = Modifier.padding(top = 10.dp))
            Text(text = "Three")
        }
    }

    @Composable
    fun ColunmTest1() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            DefaultText(str = "1")
            DefaultText(str = "2")
            DefaultText(str = "3")
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DefaultText(str = "4")
            DefaultText(str = "5")
            DefaultText(str = "6")
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            DefaultText(str = "7")
            DefaultText(str = "8")
            DefaultText(str = "9")
        }

    }

    @Composable
    @Preview(showBackground = true)
    fun ColunmTestPreview() {
        ColunmTest()
    }

    @Composable
    @Preview(showBackground = true)
    fun ColunmTest1Preview() {
        ColunmTest1()
    }


    @Composable
    fun DefaultText(str: String) {
        Column(
            modifier = Modifier.size(100.dp).background(Color.Red),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = str,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        }

    }

}
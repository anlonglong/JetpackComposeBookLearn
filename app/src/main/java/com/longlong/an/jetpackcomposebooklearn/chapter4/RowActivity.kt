package com.longlong.an.jetpackcomposebooklearn.chapter4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class RowActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    @Composable
    fun RowTest(){
        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "1", fontSize = 20.sp, modifier = Modifier.padding(20.dp), color = Color.Red)
            Text(text = "2", fontSize = 20.sp,modifier = Modifier.padding(20.dp), color = Color.Red)
            Text(text = "3", fontSize = 20.sp,modifier = Modifier.padding(20.dp), color = Color.Red)
            Text(text = "4", fontSize = 20.sp,modifier = Modifier.padding(20.dp), color = Color.Red)
        }
    }


    @Preview
    @Composable
    fun RowTestPreview(){
        RowTest()
    }

}
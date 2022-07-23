package com.longlong.an.jetpackcomposebooklearn.chapter5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class LazyColumnActivty : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Test1()}
    }

    @Composable
    fun Test1() {
        val data = createIntData()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(count = data.size, key = {
               return@items data[it]
            }) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Cyan)
                        .height(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "### ${data[index]}")
                }

            }
        }
    }


    @Preview
    @Composable
    fun TestPreview() {
        Test1()
    }

    private fun createIntData() = mutableListOf<Int>().apply {
        for (i in 0..50) {
            this.add(i)
        }
    }

}
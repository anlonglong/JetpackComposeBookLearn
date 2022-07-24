package com.longlong.an.jetpackcomposebooklearn.chapter5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
        setContent { Test3()}
    }

    @Composable
    fun Test1() {
        val data = createListData()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(count = data.size) { index ->
                ItemTest(data, index)
            }
        }
    }

    @Composable
    fun Test2() {
        val data = createListData()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(items = data){ index, item->
                ItemTest(data, index)
            }
        }
    }

    @Composable
    fun Test3() {
        val data = createArrayData()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(items = data){ index, item->
                ItemTest2(data, index)
            }
        }
    }


    @Composable
    private fun ItemTest(data: List<Int>, index:Int){
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

    @Composable
    private fun ItemTest2(data: Array<Int>, index:Int){
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


    @Preview
    @Composable
    fun TestPreview() {
        Test1()
    }

    @Preview
    @Composable
    fun TestPreview2() {
        Test2()
    }

    @Preview
    @Composable
    fun TestPreview3() {
        Test3()
    }

    private fun createListData() = mutableListOf<Int>().apply {
        for (i in 0..50) {
            this.add(i)
        }
    }

    private fun createArrayData() = Array(50){ it }

}
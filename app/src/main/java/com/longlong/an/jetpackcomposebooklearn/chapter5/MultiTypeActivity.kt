package com.longlong.an.jetpackcomposebooklearn.chapter5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MultiTypeActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test()
        }
    }


    @Composable
    fun Test() {
        val chatData = createChatData()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(chatData) { data ->
                if (data.isLeft) {
                    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.Start) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = data.content, modifier = Modifier.background(Color.Red))
                    }
                } else {
                    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.End) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = data.content, modifier = Modifier.background(Color.Blue))
                    }
                }
            }
        }
    }

    private fun createChatData() = arrayListOf<Chat>().apply {
        add(Chat("你好啊小红"))
        add(Chat("你在干啥呢"))
        add(Chat("想问你个事"))
        add(Chat("没事，还在夏代吗", false))
        add(Chat("什么事啊大哥", false))
        add(Chat("没事"))
        add(Chat("好吧", false))
    }


    @Preview
    @Composable
    fun TestPreview() {
        Test()
    }

}

data class Chat(val content: String, val isLeft: Boolean = true)
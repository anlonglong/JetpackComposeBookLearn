package com.longlong.an.jetpackcomposebooklearn.chapter5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine

@OptIn(ExperimentalFoundationApi::class)
class StickActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Test2()

        }
    }

    @Composable
    fun Test2() {
        val contract = contractData()
        val listState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()
        Box{
            LazyColumn(state = listState) {
                contract.forEach { (letter, nameList) ->
                    run {
                        stickyHeader {
                            Text(
                                text = letter,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .background(Color.Green)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontSize = 35.sp
                            )
                        }

                        items(nameList) { c ->
                            Text(
                                text = c,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .background(Color.Red)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontSize = 35.sp
                            )
                        }
                    }
                }
            }
            Button(onClick = {
                coroutineScope.launch {
                    listState.animateScrollToItem(index = 0)
                }
            }) {
                Text(text = "TOP")
            }
        }

    }

    @Composable
    fun Test() {
        val data = createChatData()
        LazyColumn {
            items(data) { item ->
                Text(
                    text = item.content,
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color.Red)
                        .height(150.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 35.sp
                )
            }

            stickyHeader {
                Text(
                    text = "我是粘性标题",
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color.Green)
                        .height(150.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 35.sp
                )
            }

            items(data) { item ->
                Text(
                    text = item.content,
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color.Red)
                        .height(150.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 35.sp
                )
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

    private fun contractData(): MutableList<Contract> {
        val letter = arrayListOf("A", "B", "C", "D", "E")
        val contracts = arrayListOf<Contract>()
        val names = arrayListOf<String>()
        for (i in 0..10) {
            names.add("Lu ren $i")
        }
        letter.forEach { it ->
            contracts.add(Contract(it, names))
        }

        return contracts
    }

    data class Chat(val content: String, val isLeft: Boolean = true)

    data class Contract(val letter: String, val names: List<String>)

}
package com.longlong.an.jetpackcomposebooklearn

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.longlong.an.jetpackcomposebooklearn.ui.theme.JetpackComposeBookLearnTheme

private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeBookLearnTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val index = rememberSaveable() {
                        mutableStateOf(0)
                    }
                    ComposeStatue3(index.value){
                        Log.d(TAG, "ComposeStatue3 = $it")
                        index.value = it
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(modifier = Modifier
        .width(100.dp)
        .height(20.dp),text = "Hello $name!")
}

@Composable
fun Greeting2(name: String, showName:Boolean){
    val showName = if (showName) "显示名字" else "不显示名字"
    Text(text = "name = $name, $showName")
}


@Composable
fun ComposeStatue(){
    Column(modifier = Modifier.fillMaxSize(),
     horizontalAlignment = Alignment.CenterHorizontally) {
        var index = 0 //这样定义的index在点击事件后是无法改变显示在text上数字的
        Button(onClick = {
            index++
            Log.i(TAG, "ComposeStatue: index = $index")
        }){
            Text(text = "Add")
        }
        Text(text = "Index = $index")
    }
}


/**
 * 这样的可组合项，自己保持自己的状态，就会变的难以复用，同时该组合项与其状态的存储
 * 紧密关联。
 */
@Composable
fun ComposeStatue2(){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        //可组合函数可以通过remember可组项记住单个对象
        var index = remember { mutableStateOf(0)}
        Log.i(TAG, "index = ${index.value}")
        Button(onClick = {
            index.value++
            Log.i(TAG, "ComposeStatue: index = ${index.value}")
        }){
            Text(text = "Add")
        }
        Text(text = "Index = ${index.value}")
    }
}


/**
 * 状态提升
 * 将自己内部的状态提升到自己的参数中，由外部参数指定。
 */
@Composable
fun ComposeStatue3(index:Int,onIndexChanged: (Int)->Unit = {}){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Log.i(TAG, "index = $index")
        Button(onClick = {
            onIndexChanged(1 + index)
            Log.i(TAG, "ComposeStatue: index = $index")
        }){
            Text(text = "Add")
        }
        Text(text = "Index = $index")
    }
}


@Preview(showBackground = true)
@Composable
fun ComposeStatue3Preview(){
    val index = rememberSaveable() { mutableStateOf(0) }
    ComposeStatue3(index.value){
       index.value = it
    }
}

@Preview(showBackground = true, widthDp = 100, heightDp = 200, name = "测试")
@Composable
fun Greeting2Preview(){
    Greeting2("Yack", false)
}

@Preview(showBackground = true, widthDp = 100, heightDp = 200, name = "测试")
@Composable
fun DefaultPreview() {
    JetpackComposeBookLearnTheme {
        Greeting("world")
    }
}


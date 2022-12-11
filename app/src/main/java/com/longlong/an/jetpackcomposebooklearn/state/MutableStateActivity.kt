package com.longlong.an.jetpackcomposebooklearn.state

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em


/** 问题stackoverflow link
 *  https://stackoverflow.com/questions/65368007/what-does-jetpack-compose-remember-actually-do-how-does-it-work-under-the-hood
 *
 *
 * remember - allows you to remember state from previous recompose invocation and just this. So if you for instance randomize color at initial run. The randomized color will going to be calculated once and reused whenever re-compose is necessary.

so ... remember = store value just in case recompose will be called.

Now the second thing is knowing when re-compose should be actually triggered. and there mutable states comes to help.

mutablestate = store the value AND in case i update value trigger recompose for all elements using this data.

1。 remember 可以帮助我们记住上一次recompose的数据，类似于我们之前的成员变量，记住一些状态或者数据，
因为在compose中一个compose函数所需要的数据一般都是在这个函数的作用域内声明的，因为compose函数有重组的这个过程，如果我们像之前的
方式直接把数据或者状态声明在compose函数的作用域中的，在compose函数重组的时候就会把我们之前的状态重置了，
为了解决上面的问题，compose提出用remember来记住我们的一些数据和状态，防止在compose函数重组的时候，重置我们之前的数据，
2。 mutablestate compose函数的数据更新渲染是由mutablestate来控制的，如果我们想要重组compose函数，就需要更新mutablestate
的值，乍样才会出发recompose，所以我们一般使用mutablestate来存储我们要在UI上要显示的值。

eg 1：
如果我们的一个UI数据只是在第一次composed的时候使用一次，后面不用载更新，我们可以这样来定义

        @Composable
        fun Test() {
        val color = remember {Color.Red} //只在初始化的时候复值一次并记住这次的颜色值，后续不会改变，类似一val 类型的成员变量，初始化只复值一次，后续无法改变。
        //var color = remember { mutableStateOf（Color.Red）} //初始化的时候复值并且记住，后续可以变这个值，类似于var类型的成员变量，后续可改变。
        Text(text = "Test", color = color)
        }
eg 2：
 如果我们的一个UI数据在第一次初始化以后，会随着用户的操作改变，并且要反应在UI界面上，我们可以如下来定义
fun Test1() {
val color = remember {     1. 用remember来记住颜色值
mutableStateOf(Color.Red)  2. 用mutableStateOf来存储颜色值并且在颜色更改的时候可以recompose用到这个值的view，
没用这个值的view不会重新渲染，即使在同一个compose函数内部
}
Button(onClick = {
color.value = Color.Blue
}) {
Text(text = "Test", color = color.value)
}
}
-------或者定义成代理的形式，方便直接使用值，不用通过color.value来那值了
@Composable
fun Test2() {
var color by remember {
mutableStateOf(Color.Red)
}
Button(onClick = {
color = Color.Blue
}) {
Text(text = "Test", color = color)
}
}
 */

private const val TAG = "MutableStateActivity"
class MutableStateActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreenContent()
        }
    }

    @OptIn(ExperimentalTextApi::class)
    @Composable
    fun MyScreenContent(names: List<String> = listOf("Android", "there")) {
        var count by remember { mutableStateOf(0)}
        Log.i(TAG, "1MyScreenContent: count = $count")
        var i = remember { 0 }
        val viewModel: MyViewModel by viewModels()
        //val v = viewModel.count.collectAsState().value
        Column(modifier = Modifier.fillMaxSize()) {
            for (name in names) {
                Greeting(name = name)
                Divided()
            }
            Counter(count = count) {
                count = it
                i++
                Log.i(TAG, "--- MyScreenContent: i = $i")
                Log.i(TAG, "2 MyScreenContent: count = $count")
            }
            Text(text = "000000 ", style = LocalTextStyle.current.merge(
                TextStyle(
                    lineHeight = 2.5.em,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.None
                    )
                )
            ))
            viewModel.beginCount()
            ViewModelDemo(viewModel)
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name")
    }

    @Composable
    fun Divided() {
        Divider(color = Color.Red, thickness = 2.dp)
    }

    @Composable
    fun Counter(count: Int, update: (Int) -> Unit) {
        Button(
            onClick = { update(count + 1) },
            colors = ButtonDefaults.buttonColors(if (count > 5) Color.Red else Color.Blue)
        ) {
            Text("I've been clicked $count times")
        }
    }

    @Composable
    fun ViewModelDemo(viewModel: MyViewModel) {
        val v = viewModel.count.collectAsState().value
        Log.i(TAG, "ViewModelDemo: ================== v= $v")

        Button(onClick = {  }) {
            Text(text = "Begin Count")
        }
        Text(text = "From view Model Count = $v",
            Modifier.background( if (v.mod(2) == 0) Color.Blue else Color.Red)
        )
    }

    @Composable
    fun Test() {
        val color = remember {
            Color.Red
        }
        Text(text = "Test", color = color)
    }

    @Composable
    fun Test1() {
        val color = remember {
            mutableStateOf(Color.Red)
        }
        Button(onClick = {
            color.value = Color.Blue
        }) {
            Text(text = "Test", color = color.value)
        }
    }

    @Composable
    fun Test2() {
        var color by remember {
            mutableStateOf(Color.Red)
        }
        Button(onClick = {
            color = Color.Blue
        }) {
            Text(text = "Test", color = color)
        }
    }

    @OptIn(ExperimentalTextApi::class)
    @Preview
    @Composable
    fun Preview() {
        Column() {
            Text(text = "000000")
            Text(text = "000000", style = LocalTextStyle.current.merge(
                TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.None
                    )
                )
            ))
        }

    }

}
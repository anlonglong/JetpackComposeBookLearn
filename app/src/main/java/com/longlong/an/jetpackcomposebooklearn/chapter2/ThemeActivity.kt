package com.longlong.an.jetpackcomposebooklearn.chapter2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.longlong.an.jetpackcomposebooklearn.ui.theme.JetpackComposeBookLearnTheme

class ThemeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeBookLearnTheme {
                Surface(color = MaterialTheme.colors.primary) {

                }
            }
        }
    }

    @Composable
    fun Text1() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
        Text(
            //MaterialTheme.colors.onBackground 调用给主题设置的onBackground的颜色
            //我们在主题中设置的颜色，字体样式，shape等的数值都会保存在MaterialTheme的这个单例中，
            //我们后续如果想使用的话，就使用MaterialTheme来获取这些保存的值。
            text = "Hello Theme", color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .width(100.dp)
                .height(20.dp)
                .fillMaxHeight()
                .background(Color.Cyan),
            style = TextStyle(
                background = Color.Blue
            )
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun Text1Preview() {
        Text1()
    }
}
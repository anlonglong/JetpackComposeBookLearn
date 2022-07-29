package com.longlong.an.jetpackcomposebooklearn.chapter7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/**
 *
 */
class Animation1Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @Composable
    fun EasyAnimation() {
        val visible = remember { mutableStateOf(true) }
        Column(
            modifier = Modifier
                .size(360.dp)
                .padding(10.dp)
        ) {
            Button(onClick = { visible.value = !visible.value }) {
                Text(text = "可见性动画")
            }
            AnimatedVisibility(visible = visible.value) {
                Text(
                    text = "ADFGHJKLjbdfbkdscedsjkbcksjbjsdkbcse",
                    modifier = Modifier.size(150.dp),
                )
            }
        }
    }

    @Preview
    @Composable
    fun EasyAnimationPreview() {
        EasyAnimation()
    }
}
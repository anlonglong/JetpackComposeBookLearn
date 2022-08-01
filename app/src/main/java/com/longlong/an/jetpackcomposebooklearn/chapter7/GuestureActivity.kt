package com.longlong.an.jetpackcomposebooklearn.chapter7

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

private const val TAG = "GuestureActivity"

class GuestureActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Test4() }
    }

    @Composable
    fun Test1() {
        Column(modifier = Modifier
            .size(360.dp)
            .background(Color.Cyan)
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        Log.d(TAG, "onDoubleTap")
                    },
                    onTap = {
                        Log.d(TAG, "onTap")
                    },
                    onLongPress = {
                        Log.d(TAG, "onLongPress")
                    },
                    onPress = {
                        Log.d(TAG, "onPress")
                    }
                )
            }) {

        }
    }


    @Composable
    fun Test2() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.Green)
            ) {
            }
            Spacer(modifier = Modifier.height(50.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.Red)
            ) {
            }
            Spacer(modifier = Modifier.height(50.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.Cyan)
            ) {
            }
            Spacer(modifier = Modifier.height(50.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.DarkGray)
            ) {
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }

    @Composable
    fun Test3() {
        val gradient = Brush.verticalGradient(0f to Color.Green, 1000f to Color.White)
        Box(
            Modifier
                .background(Color.LightGray)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                repeat(8) {
                    Box(
                        modifier = Modifier
                            .background(brush = gradient)
                            .height(120.dp)
                            .fillMaxWidth()
                            .verticalScroll(
                                rememberScrollState()
                            )
                    ) {
                        Text(
                            text = "Scroll here", modifier = Modifier
                                .padding(24.dp)
                                .height(200.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun Test4() {
        Box(Modifier.fillMaxSize()) {
            val x = remember {
                mutableStateOf(0)
            }

            val y = remember {
                mutableStateOf(0)
            }
            Text(
                text = "Drag text",
                modifier = Modifier
                    .offset { IntOffset(x.value, y.value) }
                    .background(Color.Blue)
                    .size(200.dp)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            x.value += dragAmount.x.toInt()
                            y.value += dragAmount.y.toInt()
                        }
                    }
            )
        }
    }
}
package com.longlong.an.jetpackcomposebooklearn.chapter7

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.Size
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


private const val TAG = "Animation2Activity"

class Animation2Activity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text4()
        }
    }


    @Composable
    fun Test1() {
        var isSmall by remember { mutableStateOf(true) }
        val size: Dp by animateDpAsState(targetValue = if (isSmall) 40.dp else 200.dp) {
            Log.i(TAG, "Animation value = $it")
        }
        Column(modifier = Modifier.padding(10.dp)) {
            Button(onClick = { isSmall = isSmall.not() }, modifier = Modifier.padding(10.dp)) {
                Text(text = "Changed Size Dp")
            }
            Box(
                modifier = Modifier
                    .background(Color.Cyan)
                    .size(size)
            ) {

            }

        }
    }


    @Composable
    fun Test2() {
        var ok = remember { mutableStateOf(false) }
        var color = remember {
            Animatable(Color.Red)
        }
        LaunchedEffect(key1 = ok, block = {
            color.animateTo(if (ok.value) Color.Yellow else Color.Green)
        })
        Box(
            modifier = Modifier
                .size(360.dp)
                .background(color = color.value)
        ) {
            Button(onClick = { ok.value = ok.value.not() }, modifier = Modifier.padding(10.dp)) {
                Text(text = "Changed Color")
            }
        }
    }

    @SuppressLint("UnusedTransitionTargetStateParameter")
    @Composable
    fun Text3() {
        var boxState: BoxState by remember {
            mutableStateOf(BoxState.Small)
        }
        val transition = updateTransition(targetState = boxState, label = "transition")
        val color = transition.animateColor(label = "") {
            boxState.color
        }
        val size = transition.animateDp(label = "") {
            boxState.size
        }
        val offset = transition.animateDp(label = "") {
            boxState.offset
        }

        val angle = transition.animateFloat(label = "") {
            boxState.angle
        }
        Column(
            modifier = Modifier
                .padding(10.dp)
                .size(360.dp)
        ) {
            Button(onClick = { boxState = !boxState}) {
                Text(text = "Transition Test")
            }
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .rotate(angle.value)
                    .offset(offset.value)
                    .size(size.value)
                    .background(color.value)
            ) {

            }
        }

    }

    @Composable
    fun Text4() {
        val infiniteTransition = rememberInfiniteTransition()
        val color by infiniteTransition.animateColor(initialValue = Color.Red, targetValue = Color.Green
            , animationSpec = infiniteRepeatable(animation = tween(1000, easing = LinearEasing),repeatMode = RepeatMode.Reverse),)
        Box(Modifier.size(360.dp).background(color)) {

        }
    }

    sealed class BoxState(val color: Color, val size: Dp, val offset: Dp, val angle: Float) {
        operator fun not() = if (this is Small) Large else Small

        object Small : BoxState(Color.Blue, 60.dp, 20.dp, 0f)
        object Large : BoxState(Color.Red, 200.dp, 20.dp, 90f)
    }


}
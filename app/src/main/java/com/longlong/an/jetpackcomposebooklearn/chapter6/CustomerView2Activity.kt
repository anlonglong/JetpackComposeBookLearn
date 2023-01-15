package com.longlong.an.jetpackcomposebooklearn.chapter6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class CustomerView2Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Composable
    fun Test1() {
        val start = Offset(100f, 100f)
        val end = Offset(900f, 900f)
        Canvas(modifier = Modifier.size(360.dp)) {
            drawLine(
                color = Color.Yellow,
                start = start,
                end = end,
                strokeWidth = 30f,
                cap = StrokeCap.Round
            )
        }
    }


    @Composable
    fun Test2() {
        val topLeft = Offset(100f, 100f)
        Canvas(modifier = Modifier.size(360.dp), onDraw = {
            drawRect(color = Color.Blue, topLeft = topLeft, size = Size(200f, 400f))
        })
    }

    @Composable
    fun Test3() {
        val topLeft = Offset(100f, 100f)
        Canvas(modifier = Modifier.size(360.dp), onDraw = {
            drawRect(
                color = Color.Blue,
                topLeft = topLeft,
                size = Size(200f, 400f),
                style = Stroke(width = 30f, miter = 4f, join = StrokeJoin.Round)
            )
        })
    }

    @Composable
    fun Test4() {
        Canvas(modifier = Modifier.size(360.dp), onDraw = {
            drawCircle(color = Color.Magenta, radius = 100f)
        })
    }

    @Composable
    fun Test5() {
        Canvas(modifier = Modifier.size(360.dp), onDraw = {
            drawCircle(
                color = Color.Magenta,
                radius = 100f,
                style = Stroke(width = 30f, miter = 4f, join = StrokeJoin.Round)
            )
        })
    }

    @Composable
    fun Test6() {
        val topleft = Offset(100f, 100f)
        val ovalSize = Size(600f, 800f)
        Canvas(modifier = Modifier.size(360.dp), onDraw = {
            drawRect(color = Color.Red, size = ovalSize, topLeft = topleft)
            drawOval(
                color = Color.DarkGray,
                topLeft = topleft,
                size = ovalSize
            )
        })
    }

    @Composable
    @Preview
    private fun Test1Preview() {
        Test1()
    }

    @Composable
    @Preview
    private fun Test1Preview2() {
        Test2()
    }

    @Composable
    @Preview
    private fun Test1Preview3() {
        Test3()
    }

    @Composable
    @Preview
    private fun Test1Preview4() {
        Test4()
    }

    @Composable
    @Preview
    private fun Test1Preview5() {
        Test5()
    }

    @Composable
    @Preview
    private fun Test1Preview6() {
        Test6()
    }

}
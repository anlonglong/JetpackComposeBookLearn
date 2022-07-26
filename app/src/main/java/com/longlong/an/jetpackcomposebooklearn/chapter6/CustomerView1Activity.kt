package com.longlong.an.jetpackcomposebooklearn.chapter6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class CustomerView1Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxWidth()) {
                CustomerView1()
                CustomerView2()
                CustomerView3()
            }
        }
    }

    @Composable
    fun CustomerView1() {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {
            val points = arrayListOf(
                Offset(100f, 100f),
                Offset(300f, 300f),
                Offset(500f, 500f),
                Offset(700f, 700f),
                Offset(900f, 900f),
            )
            drawPoints(
                points,
                PointMode.Points,
                Color.Cyan,
                strokeWidth = 50f,
                cap = StrokeCap.Round
            )
        }
    }

    @Composable
    fun CustomerView2() {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {
            val points = arrayListOf(
                Offset(100f, 100f),
                Offset(300f, 300f),
                Offset(500f, 500f),
                Offset(700f, 700f),
                Offset(900f, 900f),
            )
            drawPoints(
                points,
                PointMode.Lines,
                Color.Cyan,
                strokeWidth = 50f,
                cap = StrokeCap.Square
            )
        }
    }

    @Composable
    fun CustomerView3() {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {
            val points = arrayListOf(
                Offset(100f, 100f),
                Offset(300f, 300f),
                Offset(500f, 500f),
                Offset(700f, 700f),
                Offset(900f, 900f),
            )
            drawPoints(
                points,
                PointMode.Polygon,
                Color.Cyan,
                strokeWidth = 50f,
                cap = StrokeCap.Square
            )
        }
    }


    @Preview
    @Composable
    fun CustomerView1Preview() {
        CustomerView1()
    }


    @Preview
    @Composable
    fun CustomerView1Preview2() {
        CustomerView2()
    }


    @Preview
    @Composable
    fun CustomerView1Preview3() {
        CustomerView3()
    }
}
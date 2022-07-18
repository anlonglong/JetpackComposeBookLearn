package com.longlong.an.jetpackcomposebooklearn.chapter2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class ProgressBarActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProgressBar()
        }
    }

    @Composable
    fun ProgressBar() {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            CircularProgressIndicator(strokeWidth = ProgressIndicatorDefaults.StrokeWidth.plus(10.dp))
            CircularProgressIndicator(progress = 0.65f, modifier = Modifier.size(80.dp), color = Color.Red)
            LinearProgressIndicator()
            LinearProgressIndicator(progress = 0.65f,modifier = Modifier.size(80.dp), color = Color.Red)
        }
    }

}
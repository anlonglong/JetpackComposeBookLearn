package com.longlong.an.jetpackcomposebooklearn.modify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ModifierActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                ModifierHeightText1()
            }
        }
    }


    /* https://ithelp.ithome.com.tw/articles/10270631?sc=iThomeR */
    @Preview
    @Composable
    fun ModifierHeightText1() {
        Row(
            modifier =
            Modifier
                .width(200.dp)
                .height(IntrinsicSize.Max)
                .background(Color.Red),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box {
                Column(Modifier.width(IntrinsicSize.Min).fillMaxHeight()) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .size(20.dp, 10.dp)
                            .background(Color.Gray)
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .size(30.dp, 10.dp)
                            .background(Color.Blue)
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .size(10.dp, 10.dp)
                            .background(Color.Magenta)
                    )
                }
            }
            Box {
                Column(Modifier.width(IntrinsicSize.Max).fillMaxHeight()) {
                    Box(Modifier.fillMaxWidth().background(Color.Gray)) {
                        Text("Short text")
                    }
                    Box(Modifier.fillMaxWidth().background(Color.Blue)) {
                        Text("Extremely long text giving the width of its siblings")
                    }
                    Box(Modifier.fillMaxWidth().background(Color.Magenta)) {
                        Text("Medium length text")
                    }
                }
            }
        }
    }

}
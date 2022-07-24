package com.longlong.an.jetpackcomposebooklearn.chapter4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

class ConstranitActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { ConstraintsTest() }
    }

    @Composable
    fun ConstraintsTest(){
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (one, two) = createRefs()
            val three = createRef()
            DefaultText(text = "One", modifier = Modifier.constrainAs(one) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            })

            DefaultText(text = "Two", modifier = Modifier.constrainAs(two){
                start.linkTo(parent.start)
                top.linkTo(one.bottom, margin = 16.dp)
                end.linkTo(parent.end)
            })

            DefaultText(text = "Three", modifier = Modifier.constrainAs(three){
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom, margin = 16.dp)
                end.linkTo(parent.end)
            })
        }

    }

    @Composable
    fun ConstraintsTest2(){
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (one, two) = createRefs()
            val three = createRef()
            DefaultText(text = "One", modifier = Modifier.constrainAs(one) {
//                start.linkTo(parent.start)
//                top.linkTo(parent.top)
//                end.linkTo(parent.end)
            })

            DefaultText(text = "Two", modifier = Modifier.constrainAs(two){
//                start.linkTo(parent.start)
//                top.linkTo(one.bottom, margin = 16.dp)
//                end.linkTo(parent.end)
            })

            DefaultText(text = "Three", modifier = Modifier.constrainAs(three){
//                start.linkTo(parent.start)
//                bottom.linkTo(parent.bottom, margin = 16.dp)
//                end.linkTo(parent.end)
            })
            createVerticalChain(one, two, three)
        }

    }

    @Composable
    fun DefaultText(text: String, modifier: Modifier){
        Text(text = text, modifier = modifier
            .size(100.dp)
            .background(Color.Red), fontSize = 30.sp, textAlign = TextAlign.Center)
    }

    @Composable
    @Preview
    fun ConstraintsTestPreview(){
        ConstraintsTest2()
    }

}
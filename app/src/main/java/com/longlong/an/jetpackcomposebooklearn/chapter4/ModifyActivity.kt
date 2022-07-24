package com.longlong.an.jetpackcomposebooklearn.chapter4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ModifyActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Composable
    fun Test1(){
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "asdf", modifier = Modifier.padding(10.dp).clickable {  })
            Text(text = "ghjk", modifier = Modifier.size(50.dp, 20.dp))
        }
    }

    @Composable
    fun Test2(){
        Row(modifier = Modifier.fillMaxSize()) {
            Text(text = "asdf", modifier = Modifier.weight(1f).height(50.dp))
            Text(text = "ghjk", modifier =  Modifier.weight(2f).height(50.dp))
        }

    }


    @Preview
    @Composable
    fun TextPreview(){
        Test1()
    }

    @Preview
    @Composable
    fun TextPreview2(){
        Test2()
    }


}
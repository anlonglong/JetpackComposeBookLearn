package com.longlong.an.jetpackcomposebooklearn.effect

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "EffectActivity"
class EffectActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                //LaunchedEffectDemo()
                LaunchedEffectDemo1()
            }
        }
    }

    @Composable
    private fun LaunchedEffectDemo1() {
        val scaffoldState: ScaffoldState = rememberScaffoldState()
        val coroutineScope: CoroutineScope = rememberCoroutineScope()
        Scaffold(scaffoldState = scaffoldState) {
            Button(onClick = {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Something happened!")
                }
            }) {
                Text(text = "Click me-1!")
            }
        }
        movableContentOf {  }
    }



    @Composable
    private fun LaunchedEffectDemo() {
        val scaffoldState: ScaffoldState = rememberScaffoldState()
        var show by remember {
            mutableStateOf(false)
        }
        LaunchedEffect(key1 = show){
            if (show){
                scaffoldState.snackbarHostState.showSnackbar("Something happened!")
            }
        }
        Scaffold(scaffoldState = scaffoldState) {
            Button(onClick = {
                show = show.not()
            }) {
                Text(text = "Click me-0!")
            }
        }
    }

    suspend fun fu():String{
        delay(2000)
        return "1234"
    }
}
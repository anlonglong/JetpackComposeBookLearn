package com.longlong.an.jetpackcomposebooklearn.chapter8

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

private const val TAG = "ViewModelActivity"
class ViewModelActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Two() }
    }


    @Composable
    fun One() {
        val vm = viewModel<OneViewModel>()
        val count = vm.count.observeAsState(initial = 0)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = count.value.toString(), modifier = Modifier.padding(10.dp))
            Button(onClick = {
                vm.onCountChanged(count.value + 2)
            }) {
                Text(text = "Add Count")
            }
        }
    }

    @Composable
    fun Two() {
        val count = FlowTest().collectAsState(0)
        Log.i(TAG, "Two: count = $count")
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "count = ${count.value}", modifier = Modifier.padding(10.dp))
        }
    }

    fun FlowTest() = flow {
        for(i in 0 .. 10){
            Log.d(TAG, "FlowTest() called i = $i")
            delay(1000)
            emit(i)
        }
    }


    @Composable
    @Preview
    fun OnePreiew(){

    }
}
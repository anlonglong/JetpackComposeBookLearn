package com.longlong.an.jetpackcomposebooklearn.DerivedStateOf
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier


//https://medium.com/androiddevelopers/jetpack-compose-when-should-i-use-derivedstateof-63ce7954c11b
private const val TAG = "DerivedStateOfExampleAc"
class DerivedStateOfExampleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    private fun MainScreen() {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Log.d(TAG, "MainScreen() called")
            var a by remember { mutableStateOf(0) }
            var b by remember { mutableStateOf(0) }
            TextButton(onClick = { a++ }) {
                Text(text = "changed a")
            }

            TextButton(onClick = { b++ }) {
                Text(text = "changed b")
            }
            val sum = remember { derivedStateOf { a + b } }
            // Changing either a or b will cause CountDisplay to recompose but not trigger Example
            // to recompose. 参数必须是State类型才可以
            CountDisplay(sum)
        }
    }

    @Composable fun CountDisplay(count: State<Int>) {
        Log.d(TAG, "CountDisplay() called with: count = $count")
        Text("Count: ${count.value}")
    }

}
package com.longlong.an.jetpackcomposebooklearn.snapshotFlow

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.runtime.snapshots.asContextElement
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.longlong.an.jetpackcomposebooklearn.R
import kotlinx.coroutines.*
import java.net.URL
import kotlin.coroutines.EmptyCoroutineContext

private const val TAG = "CompositionLocalProvide"

class SnapFlowActivity : ComponentActivity() {

    private val collectionScope = CoroutineScope(EmptyCoroutineContext)

    @SuppressLint("UnrememberedMutableState", "RememberReturnType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                snapshotAsContextElementSample()
                var greeting by mutableStateOf("Hello")
                var person by mutableStateOf("Adam")
                val greetPersonFlow = snapshotFlow { "$greeting, $person" }
                LaunchedEffect(Unit) {
                    collectionScope.launch {
                        greetPersonFlow.collect {
                            println("greeting = $greeting")
                            println("person = $person")
                        }
                    }
                }

                Snapshot.withMutableSnapshot {
                    greeting = "Ahoy"
                    person = "Sean"
                }
                someScreenSample()
            }
        }
    }
}

@Suppress("RedundantSuspendModifier", "UNUSED_PARAMETER")
private suspend fun doSomethingSuspending(param: Any?): Any? = null

@OptIn(ExperimentalComposeApi::class)
fun snapshotAsContextElementSample() {
    runBlocking {
        val snapshot = Snapshot.takeSnapshot()
        try {
            withContext(snapshot.asContextElement()) {
                // Data observed by separately reading stateA and stateB are consistent with
                // the snapshot context element across suspensions
                doSomethingSuspending(someObject.stateA)
                doSomethingSuspending(someObject.stateB)
            }
        } finally {
            // Snapshot must be disposed after it will not be used again
            snapshot.dispose()
        }
    }
}

private object someObject {
    var stateA by mutableStateOf(0)
    var stateB by mutableStateOf(0)
}

@Composable
fun someScreenSample() {
    UserName()
    UserPhoto()
    UserEvent()
}

@Composable
fun UserEvent() {

    Text(text = "Event", modifier = Modifier.clickable {

    })
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(Unit) {
        delay(3000)

        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {

        }
    }
}

@Composable
fun UserName() {


}


@Composable
private fun UserPhoto() {

}

data class User(
    var name: String,
    val profilePhotoUrl: Int,
    val nameClick: () -> Unit,
    val eventClick: () -> Unit
)

data class ProfileIcon(val src: String)
package com.longlong.an.jetpackcomposebooklearn.localcomposiprovider

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.longlong.an.jetpackcomposebooklearn.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URL
val userLocal = compositionLocalOf<User> { error("Not provider") }
private const val TAG = "CompositionLocalProvide"
class CompositionLocalProviderActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                val u = User("JACK",R.drawable.ic_launcher_foreground,
                    nameClick = {

                    }, eventClick = {
                        Log.i(TAG, "onCreate: event click")
                    })
                CompositionLocalProvider(userLocal provides u) {
                    someScreenSample()
                }

            }

        }
    }

}


@Composable
fun someScreenSample() {
    UserName()
    UserPhoto()
    UserEvent()
}

@Composable
fun UserEvent() {
    val click = userLocal.current.eventClick
    Text(text = "Event", modifier = Modifier.clickable {

    })
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(Unit){
        delay(3000)
        click()
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){

        }
    }
}

@Composable
fun UserName() {
    val click = userLocal.current.nameClick
    Text(text = userLocal.current.name, modifier = Modifier.clickable {
        click()
    })
}


@Composable
private fun UserPhoto() {
    Image(painter = painterResource(userLocal.current.profilePhotoUrl), contentDescription = "")
}

data class User(
    var name: String,
    val profilePhotoUrl: Int,
    val nameClick: ()->Unit,
    val eventClick:()->Unit)
data class ProfileIcon(val src: String)
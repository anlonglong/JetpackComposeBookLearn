package com.longlong.an.jetpackcomposebooklearn.chapter9

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.CalendarView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


private const val TAG = "AndroidViewActivity"

class AndroidViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //AndroidViewPage()
            WebViewPage()
        }
    }


    @Composable
    fun AndroidViewPage() {
        AndroidView(
            factory = {
                CalendarView(it)
            },
            modifier = Modifier.fillMaxWidth(),
            update = {
                it
            })
    }


    @Composable
    fun WebViewPage() {
        val webview = rememberWebViewWithLifecycle()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "WebView") },
                    navigationIcon = {
                        IconButton(onClick = { finish() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "")
                        }
                    }
                )
            },
            content = {
             AndroidView(factory = {
                 webview
             }, update = {
                 it.settings.apply {
                     javaScriptEnabled = true
                 }
                 it.loadUrl("https://www.baidu.com")
             }
             )
            }
        )
    }


    @Composable
    fun rememberWebViewWithLifecycle(): WebView = kotlin.run {
        val localContext = LocalContext.current
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        return remember { WebView(localContext) }.run {
            val observer = rememberWebViewLifecycleObserver(webView = this)
            rememberWebViewLifecycleObserver(webView = this)
            DisposableEffect(lifecycle) {
                lifecycle.addObserver(observer)
                onDispose {
                    lifecycle.removeObserver(observer)
                }
            }
            this
        }
    }

}

@Composable
fun rememberWebViewLifecycleObserver(webView: WebView): LifecycleEventObserver = kotlin.run {
    remember {
        LifecycleEventObserver { source, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> webView.onResume()
                Lifecycle.Event.ON_PAUSE -> webView.onPause()
                Lifecycle.Event.ON_DESTROY -> webView.destroy()
                else -> Log.i(TAG, "rememberWebViewLifecycleObserver: ")
            }
        }
    }
}

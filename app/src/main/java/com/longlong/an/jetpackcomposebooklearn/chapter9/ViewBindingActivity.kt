package com.longlong.an.jetpackcomposebooklearn.chapter9

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.longlong.an.jetpackcomposebooklearn.databinding.ComposeLayoutBinding
import com.longlong.an.jetpackcomposebooklearn.databinding.ViewBindingLayoutBinding

/**
 * 目前只是支持viewBinding的方式。
 *
 * compose中使用Android的布局中的view
 */
private const val TAG = "ViewBindingActivity"
class ViewBindingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AndroidViewBindingPage() }
    }

    @Composable
    fun AndroidViewBindingPage(){
        val context = LocalContext.current
        AndroidViewBinding(factory = { inflate, parent, attachToRoot ->
          val layoutBinding = ViewBindingLayoutBinding.inflate(inflate, parent, attachToRoot)
            layoutBinding
        },
        modifier = Modifier.fillMaxSize(),
        update = {
            btnLogin.setOnClickListener {
                val name = et1.text.toString().trim()
                val pwd = et2.text.toString().trim()
                Toast.makeText(context, "asdfghj", Toast.LENGTH_LONG).show()
                login(context,name, pwd)
            }
        })
    }


    private fun login(ctx: Context, name: String, pwd: String) {
        if (name.isEmpty() || pwd.isEmpty()) {
            Log.i(TAG, "login: info error")
            return
        }
        Toast.makeText(ctx, "login info = $name, pwd = $pwd", Toast.LENGTH_LONG).show()
    }

}
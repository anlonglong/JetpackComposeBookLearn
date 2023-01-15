package com.longlong.an.jetpackcomposebooklearn.chapter9

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.Fragment
import com.longlong.an.jetpackcomposebooklearn.R
import com.longlong.an.jetpackcomposebooklearn.databinding.ComposeLayoutBinding


private const val TAG = "ComposeFragment"
class ComposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            addView(ComposeView(requireContext()).apply {
                id = R.id.compose_one
                setContent {
                    AndroidViewBindingPage()
                }
            })
            addView(Button(requireContext()).apply {
                id = R.id.compose_two
                text="Copmpose"
            })
            addView(ComposeView(requireContext()).apply {
                id = R.id.compose_three
                setContent {
                    Text(text = "测试")
                }
            })
        }
    }

    @Composable
    fun AndroidViewBindingPage(){
        val context = LocalContext.current
        AndroidViewBinding(factory = { inflate, parent, attachToRoot ->
            val layoutBinding = ComposeLayoutBinding.inflate(inflate, parent, attachToRoot)
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
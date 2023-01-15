package com.longlong.an.jetpackcomposebooklearn.chapter9

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.longlong.an.jetpackcomposebooklearn.databinding.FragmentLayoutBinding

class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FragmentInComposeExample()
        }
    }

    @Composable
    fun FragmentInComposeExample() {
        AndroidViewBinding(FragmentLayoutBinding::inflate) {

        }
    }

}
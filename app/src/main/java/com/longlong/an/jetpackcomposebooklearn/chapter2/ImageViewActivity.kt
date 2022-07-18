package com.longlong.an.jetpackcomposebooklearn.chapter2

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.util.CoilUtils
import com.longlong.an.jetpackcomposebooklearn.R

class ImageViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Composable
    fun ImageTest() {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Image",
            alignment = Alignment.BottomEnd
        )
    }

    @Composable
    fun ImageTest1() {
        val ima = BitmapFactory.decodeResource(
            LocalContext.current.resources,
            androidx.appcompat.R.drawable.abc_btn_radio_material
        )
        Image(bitmap = ima.asImageBitmap(), contentDescription = "Image")
    }

    @Composable
    fun ImageTest2() {
        Box() {
            Text(text = "哈哈")
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Image",
                alignment = Alignment.BottomEnd, alpha = 0.5f,
                colorFilter = ColorFilter.tint(Color.Red)
            )
        }
    }

    @Composable
    fun ImageTest3() {
        AsyncImage(model = , contentDescription = )
    }

    @Preview(showBackground = true, widthDp = 200, heightDp = 100)
    @Composable
    fun ImageTestPreview() {
        ImageTest()
    }

    @Preview(showBackground = true)
    @Composable
    fun ImageTestPreview1() {
        ImageTest1()
    }

    @Preview(showBackground = true)
    @Composable
    fun ImageTestPreview2() {
        ImageTest2()
    }

}
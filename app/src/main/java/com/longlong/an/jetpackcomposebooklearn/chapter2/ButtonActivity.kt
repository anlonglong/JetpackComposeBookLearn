package com.longlong.an.jetpackcomposebooklearn.chapter2

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.longlong.an.jetpackcomposebooklearn.ui.theme.JetpackComposeBookLearnTheme

class ButtonActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            JetpackComposeBookLearnTheme() {
                Surface {
                 ButtonTest()    
                }
            }
        }
    }

    @Composable
    fun ButtonTest() {
        val current = LocalContext.current
        Button(
            onClick = { Toast.makeText(current, "Button Click", Toast.LENGTH_SHORT).show() },
            modifier = Modifier.padding(5.dp),
            elevation = ButtonDefaults.elevation(3.dp, 7.dp, 0.dp),
            border = BorderStroke(5.dp, Color.Cyan),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            Text(text = "Button")
        }
    }

    @Composable
    @Preview(showBackground = true, widthDp = 100, heightDp = 50)
    fun ButtonTestPreview() {
        ButtonTest()
    }
}
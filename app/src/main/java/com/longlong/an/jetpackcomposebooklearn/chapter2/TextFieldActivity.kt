package com.longlong.an.jetpackcomposebooklearn.chapter2


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class TextFieldActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextFieldTest1()
//            OutlineTextFieldTest1()
        }
    }

    @Composable
    fun TextFieldTest1() {
        val textStr = remember { mutableStateOf("你好") }
        val context = LocalContext.current
        TextField(
            value = textStr.value,
            onValueChange = { str ->
                textStr.value = str //改变state的状态，强制该组合函数重新绘制，刷新输入的内容
            },
            label = {
                Text(text = "Enter Text")
            },
            maxLines = 2,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                keyboardType = KeyboardType.Email,
                autoCorrect = true,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                Toast.makeText(context, "search = ${textStr.value }", Toast.LENGTH_SHORT).show()
            }),
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(20.dp),
        )
    }

    @Composable
    fun OutlineTextFieldTest1() {
        val textStr = remember { mutableStateOf("你好") }
        OutlinedTextField(value = textStr.value, onValueChange = { str ->
            textStr.value = str //改变state的状态，强制该组合函数重新绘制，刷新输入的内容
        },
            label = {
                Text(text = "Label")
            })
    }

    @Preview(showBackground = true)
    @Composable
    fun TextFieldTest1Preview() {
        TextFieldTest1()
    }

    @Preview(showBackground = true)
    @Composable
    fun OutlineTextFieldTest1Preview() {
        OutlineTextFieldTest1()
    }

}
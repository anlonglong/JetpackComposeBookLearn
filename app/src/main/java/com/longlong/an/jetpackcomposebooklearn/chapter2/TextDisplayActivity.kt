package com.longlong.an.jetpackcomposebooklearn.chapter2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.longlong.an.jetpackcomposebooklearn.ComposeStatue3
import com.longlong.an.jetpackcomposebooklearn.R
import com.longlong.an.jetpackcomposebooklearn.ui.theme.JetpackComposeBookLearnTheme

private const val TAG = "TextDisplayActivity"

class TextDisplayActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackComposeBookLearnTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        ClickTextTest()
                    }
                }
            }
        }
    }

    //精准版本定位
    @Composable
    fun ClickTextTest() {
        val annotatedString = buildAnnotatedString {
            append("点击")
            pushStringAnnotation(tag = "URL", annotation = "https://github.com/settings/tokens")
            withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                append("Url")
            }
            pop()
        }
        ClickableText(text = annotatedString, style = TextStyle(fontSize = 30.sp),onClick = { offset ->
            Log.i(TAG, "AA: it = $offset")
            annotatedString.getStringAnnotations("URL",offset,offset).firstOrNull()?.let { ann->
                Log.i(TAG, "BB = $ann")
            }

        })
    }

}

@Composable
fun TextTest() {
    Text(
        text = stringResource(id = R.string.text1),
        color = Color.Red,
        fontSize = 10.sp,
        fontStyle = FontStyle.Italic, //斜体展示文本，
        fontWeight = FontWeight.Bold, //字体加粗
        letterSpacing = 2.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .width(100.dp)
            .background(Color.Blue),
        lineHeight = 20.sp,
    )
}


@Composable
fun TextTest2() {
    //FontFamily 也可以使用自定义的字体来展示
    Column {
        Text(
            text = "Hello world",
            fontFamily = FontFamily.Default,
            textDecoration = TextDecoration.None
        )
        Text(
            text = "Hello world",
            fontFamily = FontFamily.Serif,
            textDecoration = TextDecoration.LineThrough
        )
        Text(
            text = "Hello world",
            fontFamily = FontFamily.Monospace,
            textDecoration = TextDecoration.Underline
        )
        Text(text = "Hello world", fontFamily = FontFamily.Cursive)
        Text(text = "Hello world", fontFamily = FontFamily.SansSerif)
    }
}

@Composable
fun AnnotationStringTest() {
    Text(buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Blue)) {
            append("A")
        }
        append("n")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
            append("L")
        }
        append("ong Long")
    })
}


//类似于SpannableString
@Composable
fun AnnotationStringTest2() {
    Text(buildAnnotatedString {
        withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("Hello\n")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                append("World\n")
            }

            append("compose")
        }
    })
}


@Composable
fun SelectionContainerTest() {
    //长安选择文字
    SelectionContainer(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(text = "素胚勾勒出青花筆鋒濃轉淡", maxLines = 1, overflow = TextOverflow.Clip)
            Text(text = "瓶身描繪的牡丹一如你初妝", maxLines = 1, overflow = TextOverflow.Ellipsis)
            DisableSelection {
                //这部分的文字是不响应长按复制文字的。
                Text(text = "冉冉檀香透過窗心事我了然")
                Text(text = "宣紙上走筆至此擱一半")
            }
            Text(text = "素胚勾勒出青花筆鋒濃轉淡")
        }

    }
}

@Preview(showBackground = true, widthDp = 100, heightDp = 50)
@Composable
fun TextTestPreview() {
    TextTest()
}

@Preview(showBackground = true, widthDp = 100, heightDp = 200)
@Composable
fun SelectionContainerTestPreview() {
    SelectionContainerTest()
}

@Preview(showBackground = true, widthDp = 100, heightDp = 50)
@Composable
fun AnnotationStringTestPreview() {
    AnnotationStringTest()
}

@Preview(showBackground = true, widthDp = 100, heightDp = 50)
@Composable
fun AnnotationStringTest2Preview() {
    AnnotationStringTest2()
}


@Preview(showBackground = true, widthDp = 200, heightDp = 100)
@Composable
fun TextTestPreview2() {
    TextTest2()
}
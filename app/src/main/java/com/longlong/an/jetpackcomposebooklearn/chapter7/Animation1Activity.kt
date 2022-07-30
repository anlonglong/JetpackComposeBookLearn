package com.longlong.an.jetpackcomposebooklearn.chapter7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 *
 */
class Animation1Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyAnimation2()
        }
    }

    @Composable
    fun EasyAnimation() {
        val visible = remember { mutableStateOf(true) }
        Column(
            modifier = Modifier
                .size(360.dp)
                .padding(10.dp)
        ) {
            Button(onClick = { visible.value = !visible.value }) {
                Text(text = "可见性动画")
            }
            AnimatedVisibility(
                visible = visible.value,
                enter = slideIn(initialOffset = { IntOffset(400, 400) }) + expandIn(),
                exit = slideOut(targetOffset = { IntOffset(400, 400) }) + shrinkOut()
            ) {
                Text(
                    text = "ADFGHJKLjbdfbkdscedsjkbcksjbjsdkbcse",
                    modifier = Modifier.size(150.dp),
                )
            }
        }
    }


    @Composable
    fun EasyAnimation2() {
        val expend = remember { mutableStateOf(true) }
        val expend2 by remember { mutableStateOf(true) }
        Column(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            Text(
                text = "如果链表尾部有环，如果一个节点枚举到后面会在闭环中不断循环枚举，那么怎么样能高效判断有环并且能快速终止呢？\n" +
                        "有环，其实就是第二次、第三次走过这条路才能说它有环，一个指针在不借助太多空间存储状态下无法有效判断是否有环(有可能链表很长、有可能已经在循环了)，咱们可以借助 快慢指针(双指针) 啊。\n" +
                        "其核心思想就是利用两个指针：快指针(fast)和慢指针(slow),它们两个同时从链表头遍历链表，只不过两者速度不同，如果存在环那么最终会在循环链表中相遇。\n" +
                        "我们在具体实现的时候，可以快指针(fast)每次走两步，慢指针(slow)每次走一步。如果存在环的话快指针先进入环，慢指针后入环，在慢指针到达末尾前快指针会追上慢指针。\n" +
                        "快慢指针如果有相遇那就说明有环，如果快指针先为null那就说明没环。\n" +
                        "\n" +
                        "作者：不秃顶的Java程序员\n" +
                        "链接：https://juejin.cn/post/6971340474778910728\n" +
                        "来源：稀土掘金\n" +
                        "著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。",
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.animateContentSize(),
                maxLines = expend.value.takeIf { it }?.run { Int.MAX_VALUE } ?: kotlin.run { 2 }
            )

            Text(text = expend.value.takeIf { it }?.run { "close" } ?: kotlin.run { "open" },
                color = Color.Red,
                modifier = Modifier.clickable {
                    expend.value = expend.value.not()
                })
        }
    }

    @Preview
    @Composable
    fun EasyAnimationPreview() {
        EasyAnimation()
    }
}
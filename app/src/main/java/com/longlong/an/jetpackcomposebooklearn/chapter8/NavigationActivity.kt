package com.longlong.an.jetpackcomposebooklearn.chapter8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class NavigationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NavigationTest() }
    }

    @Composable
    fun NavigationTest() {
        val navi = rememberNavController()
        NavHost(navController = navi, startDestination = "one_page") {
            composable("one_page") {
                OnePage(navi)
            }

            composable("two_page/{name}") {
                TwoPage(navi,it.arguments?.getString("name", "") ?:"")
            }
        }
    }

    private @Composable
    fun TwoPage(navi: NavHostController,arg: String) {
        BasePage(arg){
            navi.navigate("one_page")
        }
    }

    @Composable
    fun OnePage(navi: NavHostController) {
        BasePage("One"){
            navi.navigate("two_page/anlonglong")
            navi.navigateUp()
        }
    }

    @Composable
    fun BasePage(content: String, onClick: () -> Unit) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = content,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                modifier = Modifier.clickable { onClick() })
        }
    }

}
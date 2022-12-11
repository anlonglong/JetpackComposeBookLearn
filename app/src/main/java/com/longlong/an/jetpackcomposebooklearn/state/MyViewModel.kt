package com.longlong.an.jetpackcomposebooklearn.state

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val TAG = "MyViewModel"
class MyViewModel : ViewModel() {

    private val _count: MutableStateFlow<Int> = MutableStateFlow(0)

    init {
        Log.i(TAG, "Init----------")
        //beginCount()
    }

    val count = _count.asStateFlow().stateIn(
        scope = viewModelScope,
        SharingStarted.Eagerly,
        _count.value
    )

    fun beginCount() {
        viewModelScope.launch {
            for(i in 0 .. 10){
                delay(5000)
                _count.update { i }
            }

        }
    }

}
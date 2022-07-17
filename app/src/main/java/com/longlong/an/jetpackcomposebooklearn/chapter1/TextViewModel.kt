package com.longlong.an.jetpackcomposebooklearn.chapter1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextViewModel : ViewModel() {
    private var _index = MutableLiveData(0)
    val index: LiveData<Int>
        get() = _index
    fun onIndexChanged(index: Int){
        _index.value = index
    }
}
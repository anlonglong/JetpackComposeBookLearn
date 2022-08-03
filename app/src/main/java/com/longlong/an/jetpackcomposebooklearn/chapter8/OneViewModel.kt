package com.longlong.an.jetpackcomposebooklearn.chapter8

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OneViewModel : ViewModel() {

    private val _count: MutableLiveData<Int> = MutableLiveData(0)

    val count: LiveData<Int>
        get() = _count

    fun onCountChanged(count: Int) {
        _count.value = count
    }

}
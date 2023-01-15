package com.longlong.an.jetpackcomposebooklearn.application

import kotlinx.coroutines.flow.Flow

interface ApplicationForegroundStatusRegistry {

    //for compose
    val foregroundStatusFlow: Flow<Boolean>

    val isInForeground: Boolean
    val isInBackground: Boolean

    fun addObserver(observer: Observer)
    fun removeObserver(observer: Observer)

    fun interface Observer {
        fun onApplicationForegroundStatusChanged(isInForeground: Boolean)
    }
}
package com.longlong.an.jetpackcomposebooklearn.application

import android.app.Application
import android.util.Log
import androidx.compose.runtime.collectAsState
import kotlin.properties.Delegates

private const val TAG = "App"
class App : Application() {

    private val mForegroundStatusRegistry by lazy { ApplicationForegroundStatusRegistryImpl() }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(mForegroundStatusRegistry)
        mForegroundStatusRegistry.addObserver{
            Log.d(TAG, "isInForeground = $it")
        }
    }
}
package com.longlong.an.jetpackcomposebooklearn.application

import android.app.Activity
import android.app.Application
import androidx.annotation.GuardedBy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.util.Collections

class ApplicationForegroundStatusRegistryImpl:
    ApplicationForegroundStatusRegistry,
    Application.ActivityLifecycleCallbacks by SimpleActivityLifecycleCallbacks() {

    private val lock = Any()

    @GuardedBy("lock")
    private var startedActivityCounter = 0
    @GuardedBy("lock")
    private val observers = Collections.synchronizedSet(HashSet<ApplicationForegroundStatusRegistry.Observer>())

    private val mForegroundStatusFlow = MutableStateFlow(isInForeground)

    override val foregroundStatusFlow: Flow<Boolean>
        get() = mForegroundStatusFlow

    override val isInForeground: Boolean get() {
        return synchronized(lock) {
            startedActivityCounter > 0
        }
    }

    override val isInBackground: Boolean get() = !isInForeground

    override fun addObserver(observer: ApplicationForegroundStatusRegistry.Observer) {
        synchronized(lock) {
            observers.add(observer)
        }
    }

    override fun removeObserver(observer: ApplicationForegroundStatusRegistry.Observer) {
        synchronized(lock) {
            observers.remove(observer)
        }
    }

    override fun onActivityStarted(activity: Activity) {
        synchronized(lock) {
            val newCount = ++startedActivityCounter
            if (newCount == 1) {
                dispatchApplicationForegroundStatus(isInForeground = true)
            }
        }
    }

    override fun onActivityStopped(activity: Activity) {
        synchronized(lock) {
            val newCount = --startedActivityCounter
            if (newCount == 0) {
                dispatchApplicationForegroundStatus(isInForeground = false)
            }
        }
    }

    private fun dispatchApplicationForegroundStatus(isInForeground: Boolean) {
        mForegroundStatusFlow.update { isInForeground }
        synchronized(lock) {
            observers.forEach { observer ->
                observer.onApplicationForegroundStatusChanged(isInForeground = isInForeground)
            }
        }
    }
}
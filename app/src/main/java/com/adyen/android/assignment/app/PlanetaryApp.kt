package com.adyen.android.assignment.app

import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.adyen.android.assignment.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PlanetaryApp : CoreApplication() {
    override fun onCreate() {
        super.onCreate()
    }
}

abstract class CoreApplication : Application(), LifecycleEventObserver {
    var isAppInForeground: Boolean = true

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ActivityLifecycleCallback())
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> Unit
            Lifecycle.Event.ON_START -> onAppForegrounded()
            Lifecycle.Event.ON_RESUME -> Unit
            Lifecycle.Event.ON_PAUSE -> Unit
            Lifecycle.Event.ON_STOP -> onAppBackgrounded()
            Lifecycle.Event.ON_DESTROY -> Unit
            Lifecycle.Event.ON_ANY -> Unit
        }
    }

    open fun onAppBackgrounded() {
        isAppInForeground = false
    }

    open fun onAppForegrounded() {
        isAppInForeground = true
    }
}


class ActivityLifecycleCallback : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activity.allowDebugRotation()
        activity.registerFragmentLifecycleCallbacks()
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}

private fun Activity.allowDebugRotation() {
    requestedOrientation = if (BuildConfig.DEBUG) {
        ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    } else {
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}

fun Activity.registerFragmentLifecycleCallbacks() {
    if (this is FragmentActivity) {
        supportFragmentManager
            .registerFragmentLifecycleCallbacks(
                object : FragmentManager.FragmentLifecycleCallbacks() {
                    override fun onFragmentViewCreated(
                        fm: FragmentManager,
                        f: Fragment,
                        v: View,
                        savedInstanceState: Bundle?
                    ) {
                        super.onFragmentViewCreated(fm, f, v, savedInstanceState)
                    }

                    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
                        super.onFragmentResumed(fm, f)
                    }

                    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
                        super.onFragmentPaused(fm, f)
                    }

                    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
                        super.onFragmentViewDestroyed(fm, f)
                    }
                },
                true
            )
    }
}
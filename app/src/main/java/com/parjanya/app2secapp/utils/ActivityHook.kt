package com.parjanya.app2secapp.utils

import android.app.Activity
import de.robv.android.xposed.XC_MethodHook
import java.lang.ref.WeakReference

class ActivityHook : XC_MethodHook() {
    companion object {
        var currentActivity: WeakReference<Activity?> = WeakReference(null)
    }

    override fun afterHookedMethod(param: MethodHookParam?) {
        currentActivity = WeakReference(param?.result as Activity?)
    }
}
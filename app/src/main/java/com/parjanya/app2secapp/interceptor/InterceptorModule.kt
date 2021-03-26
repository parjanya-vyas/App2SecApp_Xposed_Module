package com.parjanya.app2secapp.interceptor

import android.content.Intent
import com.parjanya.app2secapp.utils.ActivityHook
import com.parjanya.app2secapp.wrapper.base.SecWrapperFactory
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class InterceptorModule : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName == "android.app") {
            val instrumentation = XposedHelpers.findClass("android.app.Instrumentation", lpparam.classLoader)
            val method = instrumentation.getMethod("newActivity", ClassLoader::class.java, String::class.java, Intent::class.java)
            XposedBridge.hookMethod(method, ActivityHook())
        }
        SecWrapperFactory.getWrapper(lpparam.packageName)?.initHooks(lpparam.classLoader)
    }
}
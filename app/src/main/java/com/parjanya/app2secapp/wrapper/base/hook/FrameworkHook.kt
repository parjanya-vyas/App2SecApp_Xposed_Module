package com.parjanya.app2secapp.wrapper.base.hook

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

class FrameworkHook(private val className: String,
                    private val hooks: List<HookMethodInfo>) {

    private fun hookMethod(hook: HookMethodInfo, classLoader: ClassLoader) {
        XposedHelpers.findAndHookMethod(className, classLoader, hook.methodName, object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam?) {
                hook.beforeHook(param)
            }
        })
    }

    private fun hookConstructor(hook: HookMethodInfo, classLoader: ClassLoader) {
        XposedHelpers.findAndHookConstructor(className, classLoader, object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam?) {
                hook.beforeHook(param)
            }
        })
    }

    fun init(classLoader: ClassLoader) {
        hooks.forEach { hook ->
            when(hook.isConstructor()) {
                true -> hookConstructor(hook, classLoader)
                false -> hookMethod(hook, classLoader)
            }
        }
    }
}
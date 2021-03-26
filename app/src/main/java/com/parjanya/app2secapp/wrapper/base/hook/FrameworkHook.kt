package com.parjanya.app2secapp.wrapper.base.hook

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

class FrameworkHook(private val className: String,
                    private val hooks: List<HookMethodInfo>) {

    private fun hookMethod(hook: HookMethodInfo, classLoader: ClassLoader) {
        XposedBridge.log("Initializing before hook for $className::${hook.methodName}")
        XposedHelpers.findAndHookMethod(className, classLoader, hook.methodName, object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam?) {
                XposedBridge.log("Intercepting hooked method $className::${hook.methodName}")
                hook.beforeHook(param)
            }
        })
    }

    private fun hookConstructor(hook: HookMethodInfo, classLoader: ClassLoader) {
        XposedBridge.log("Initializing before hook for $className::constructor")
        XposedHelpers.findAndHookConstructor(className, classLoader, object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam?) {
                XposedBridge.log("Intercepting hooked constructor for $className")
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
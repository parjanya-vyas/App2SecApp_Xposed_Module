package com.parjanya.app2secapp.wrapper.base

import com.parjanya.app2secapp.wrapper.base.hook.FrameworkHook
import com.parjanya.app2secapp.wrapper.base.hook.HookMethodInfo

interface SecWrapper {
    val packageName: String
    val className: String
    val hooks: List<HookMethodInfo>

    fun initHooks(classLoader: ClassLoader) {
        FrameworkHook(className, hooks).init(classLoader)
    }
}
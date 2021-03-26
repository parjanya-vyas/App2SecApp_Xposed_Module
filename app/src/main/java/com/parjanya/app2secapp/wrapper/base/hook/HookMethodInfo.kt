package com.parjanya.app2secapp.wrapper.base.hook

import android.content.Context
import com.parjanya.app2secapp.utils.ActivityHook
import com.parjanya.app2secapp.utils.DBHelper
import com.parjanya.app2secapp.utils.LeakAlert
import com.parjanya.app2secapp.utils.Permission
import de.robv.android.xposed.XC_MethodHook

interface HookMethodInfo {
    val methodName: String
    fun beforeHook(context: Context?)
    fun isConstructor(): Boolean = false
    fun getContext(params: XC_MethodHook.MethodHookParam?): Context? {
        return ActivityHook.currentActivity.get()
    }
    fun beforeHook(params: XC_MethodHook.MethodHookParam?) {
        beforeHook(getContext(params))
    }
}

class SourceHookMethodInfo(override val methodName: String = "", val permissions: List<Permission>,
                           private val extractContext: ((XC_MethodHook.MethodHookParam?) -> Context?)? = null) : HookMethodInfo {
    override fun beforeHook(context: Context?) {
        permissions.forEach { DBHelper.getInstance(context).insertPermission(it.toString()) }
    }

    override fun getContext(params: XC_MethodHook.MethodHookParam?): Context? {
        return extractContext?.invoke(params) ?: super.getContext(params)
    }
}

class SinkHookMethodInfo(override val methodName: String,
                         val messageFooter: String,
                         val positiveToastText: String,
                         val negativeToastText: String,
                         private val extractContext: ((XC_MethodHook.MethodHookParam?) -> Context?)? = null) : HookMethodInfo {
    override fun beforeHook(context: Context?) {
        LeakAlert(context = context,
                messageFooter = messageFooter,
                positiveToastText = positiveToastText,
                negativeToastText = negativeToastText
        ).show()
    }

    override fun getContext(params: XC_MethodHook.MethodHookParam?): Context? {
        return extractContext?.invoke(params) ?: super.getContext(params)
    }
}
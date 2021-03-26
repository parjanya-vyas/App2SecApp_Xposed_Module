package com.parjanya.app2secapp.wrapper.sink

import com.parjanya.app2secapp.wrapper.base.SecWrapper
import com.parjanya.app2secapp.wrapper.base.hook.HookMethodInfo
import com.parjanya.app2secapp.wrapper.base.hook.SinkHookMethodInfo

object EnvironmentWrapper : SecWrapper {
    override val packageName: String = "android.os"
    override val className: String = "Environment"
    override val hooks: List<HookMethodInfo> = arrayListOf(
            createStorageHook("getDataDirectory"),
            createStorageHook("getDownloadCacheDirectory"),
            createStorageHook("getExternalStorageDirectory"),
            createStorageHook("getExternalStoragePublicDirectory"),
            createStorageHook("getRootDirectory")
    )
    private fun createStorageHook(methodName: String): SinkHookMethodInfo {
        return SinkHookMethodInfo(
                methodName = methodName,
                messageFooter = " It wants to access the external storage.",
                positiveToastText = "Access Granted",
                negativeToastText = "Access Denied"
        )
    }
}
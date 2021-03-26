package com.parjanya.app2secapp.wrapper.sink

import com.parjanya.app2secapp.wrapper.base.SecWrapper
import com.parjanya.app2secapp.wrapper.base.hook.HookMethodInfo
import com.parjanya.app2secapp.wrapper.base.hook.SinkHookMethodInfo

object ContextWrapper : SecWrapper {
    override val packageName: String = "android.app"
    override val className: String = "ContextImpl"
    override val hooks: List<HookMethodInfo> = arrayListOf(
            createStorageHook("getDataDir"),
            createStorageHook("getDir"),
            createStorageHook("getExternalCacheDir"),
            createStorageHook("getExternalCacheDirs"),
            createStorageHook("getExternalFilesDir"),
            createStorageHook("getExternalFilesDirs"),
            createStorageHook("getExternalMediaDirs"),
            createStorageHook("getFileStreamPath"),
            createStorageHook("getFilesDir"),
            createStorageHook("getNoBackupFilesDir"),
            createStorageHook("getObbDir"),
            createStorageHook("getObbDirs"),
            createStorageHook("openFileOutput")
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
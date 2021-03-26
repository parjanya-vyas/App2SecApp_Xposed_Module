package com.parjanya.app2secapp.wrapper.source

import com.parjanya.app2secapp.utils.Permission
import com.parjanya.app2secapp.wrapper.base.SecWrapper
import com.parjanya.app2secapp.wrapper.base.hook.HookMethodInfo
import com.parjanya.app2secapp.wrapper.base.hook.SourceHookMethodInfo

object MediaRecorderWrapper : SecWrapper {
    override val packageName: String = "android.media"
    override val className: String = "MediaRecorder"
    override val hooks: List<HookMethodInfo> = arrayListOf(
            SourceHookMethodInfo(
                    methodName = "start",
                    permissions = listOf(Permission.RECORD_AUDIO)
            )
    )
}
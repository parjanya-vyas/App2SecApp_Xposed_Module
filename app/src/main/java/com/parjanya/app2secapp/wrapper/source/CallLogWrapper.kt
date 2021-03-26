package com.parjanya.app2secapp.wrapper.source

import com.parjanya.app2secapp.utils.Permission
import com.parjanya.app2secapp.wrapper.base.SecWrapper
import com.parjanya.app2secapp.wrapper.base.hook.HookMethodInfo
import com.parjanya.app2secapp.wrapper.base.hook.SourceHookMethodInfo

object CallLogWrapper : SecWrapper {
    override val packageName: String = "android.provider"
    override val className: String = "CallLog"
    override val hooks: List<HookMethodInfo> = arrayListOf(
            SourceHookMethodInfo(
                    methodName = "getLastOutgoingCall",
                    permissions = listOf(Permission.READ_CALL_LOG)
            )
    )
}
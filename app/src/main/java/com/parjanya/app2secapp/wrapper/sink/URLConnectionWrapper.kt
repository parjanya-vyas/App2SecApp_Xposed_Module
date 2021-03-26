package com.parjanya.app2secapp.wrapper.sink

import com.parjanya.app2secapp.wrapper.base.SecWrapper
import com.parjanya.app2secapp.wrapper.base.hook.HookMethodInfo
import com.parjanya.app2secapp.wrapper.base.hook.SinkHookMethodInfo

object URLConnectionWrapper : SecWrapper {
    override val packageName: String = "com.squareup.okhttp.internal.huc"
    override val className: String = "URLConnection"
    override val hooks: List<HookMethodInfo> = listOf(
            SinkHookMethodInfo(
                methodName = "getOutputStream",
                messageFooter = " It wants to write some data to a server.",
                positiveToastText = "Output Stream Established",
                negativeToastText = "Output Stream Not Established",
            )
    )
}
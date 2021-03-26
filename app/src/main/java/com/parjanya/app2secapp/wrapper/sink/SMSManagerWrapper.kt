package com.parjanya.app2secapp.wrapper.sink

import com.parjanya.app2secapp.wrapper.base.SecWrapper
import com.parjanya.app2secapp.wrapper.base.hook.HookMethodInfo
import com.parjanya.app2secapp.wrapper.base.hook.SinkHookMethodInfo

object SMSManagerWrapper : SecWrapper {
    override val packageName: String = "android.telephony"
    override val className: String = "SmsManager"
    override val hooks: List<HookMethodInfo> = arrayListOf(
            SinkHookMethodInfo(
                    methodName = "sendTextMessage",
                    messageFooter = " It wants to send an SMS.",
                    positiveToastText = "SMS sent",
                    negativeToastText = "SMS not sent"
            ),
            SinkHookMethodInfo(
                    methodName = "sendTextMessageWithoutPersisting",
                    messageFooter = " It wants to send an SMS.",
                    positiveToastText = "SMS sent",
                    negativeToastText = "SMS not sent"
            ),
            SinkHookMethodInfo(
                    methodName = "sendMultimediaMessage",
                    messageFooter = " It wants to send an MMS.",
                    positiveToastText = "MMS sent",
                    negativeToastText = "MMS not sent"
            ),
            SinkHookMethodInfo(
                    methodName = "sendDataMessage",
                    messageFooter = " It wants to send a data SMS.",
                    positiveToastText = "SMS sent",
                    negativeToastText = "SMS not sent"
            )
    )
}
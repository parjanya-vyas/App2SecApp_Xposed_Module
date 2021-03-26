 package com.parjanya.app2secapp.wrapper.both

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import com.parjanya.app2secapp.utils.ActivityHook
import com.parjanya.app2secapp.utils.DBHelper
import com.parjanya.app2secapp.utils.LeakAlert
import com.parjanya.app2secapp.utils.Permission
import com.parjanya.app2secapp.wrapper.base.SecWrapper
import com.parjanya.app2secapp.wrapper.base.hook.HookMethodInfo
import de.robv.android.xposed.XC_MethodHook

 object IntentWrapper : SecWrapper {
    override val packageName: String = "android.content"
    override val className: String = "Intent"
    override val hooks: List<HookMethodInfo> = arrayListOf(
            object : HookMethodInfo {
                override val methodName: String = ""
                override fun isConstructor(): Boolean = true
                override fun beforeHook(context: Context?) {
                    //do nothing since it will never be called
                    //because we are also going to override
                    //other beforeHook method
                }

                override fun beforeHook(params: XC_MethodHook.MethodHookParam?) {
                    if (params?.args.isNullOrEmpty()) return
                    if (params!!.args.isNotEmpty() && params.args[0] is String) {
                        when(params.args[0]) {
                            MediaStore.ACTION_IMAGE_CAPTURE ->
                                DBHelper.getInstance(ActivityHook.currentActivity.get()).insertPermission(Permission.CAMERA.toString())
                            Intent.ACTION_CALL ->
                                LeakAlert(
                                        context = ActivityHook.currentActivity.get(),
                                        messageFooter = " It wants to make a call.",
                                        positiveToastText = "Calling",
                                        negativeToastText = "No call made"
                                ).show()
                        }
                    }
                }
            },
            object : HookMethodInfo {
                override val methodName: String = "setType"
                override fun beforeHook(context: Context?) {
                    //do nothing since it will never be called
                    //because we are also going to override
                    //other beforeHook method
                }

                override fun beforeHook(params: XC_MethodHook.MethodHookParam?) {
                    if (params?.args.isNullOrEmpty()) return
                    if (params!!.args[0] is String) {
                        val (messageFooter, positiveMsg, negativeMsg) = when(params.args[0]) {
                            "vnd.android-dir/mms-sms" ->
                                Triple(" It wants to send a message.", "SMS sent", "SMS not sent")
                            "vnd.android.cursor.item/event" ->
                                Triple(" It wants to add an event to Calendar.", "Calendar influenced", "Calendar not influenced")
                            else -> Triple("", "", "")
                        }
                        if (messageFooter.isNotBlank()) {
                            LeakAlert(
                                    context = ActivityHook.currentActivity.get(),
                                    messageFooter = messageFooter,
                                    positiveToastText = positiveMsg,
                                    negativeToastText = negativeMsg
                            ).show()
                        }
                    }
                }
            },
            object : HookMethodInfo {
                override val methodName: String = "setData"
                override fun beforeHook(context: Context?) {
                    //do nothing since it will never be called
                    //because we are also going to override
                    //other beforeHook method
                }

                override fun beforeHook(params: XC_MethodHook.MethodHookParam?) {
                    if (params?.args.isNullOrEmpty()) return
                    if (params!!.args[0] is Uri && params.args[0].toString() == "com.android.calendar") {
                        LeakAlert(
                                context = ActivityHook.currentActivity.get(),
                                messageFooter = " It wants to add an event to Calendar.",
                                positiveToastText = "Calendar influenced",
                                negativeToastText = "Calendar not influenced"
                        ).show()
                    }
                }
            }
    )
}
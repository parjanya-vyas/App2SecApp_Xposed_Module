package com.parjanya.app2secapp.wrapper.both

import android.content.Context
import android.net.Uri
import com.parjanya.app2secapp.utils.addPermissionToDb
import com.parjanya.app2secapp.utils.showSinkDialog
import com.parjanya.app2secapp.wrapper.base.SecWrapper
import com.parjanya.app2secapp.wrapper.base.hook.HookMethodInfo
import de.robv.android.xposed.XC_MethodHook

object ContentResolverWrapper : SecWrapper {
    override val packageName: String = "android.app"
    override val className: String = "ContextImpl\$ApplicationContentResolver"
    override val hooks: List<HookMethodInfo> = arrayListOf(
            object : HookMethodInfo {
                override val methodName: String = "query"
                override fun beforeHook(context: Context?) {
                    //do nothing
                }

                override fun beforeHook(params: XC_MethodHook.MethodHookParam?) {
                    if (params?.args.isNullOrEmpty()) return
                    (params!!.args[0] as? Uri)?.addPermissionToDb()
                }
            },
            object : HookMethodInfo {
                override val methodName: String = "insert"
                override fun beforeHook(context: Context?) {
                    //do nothing
                }
                override fun beforeHook(params: XC_MethodHook.MethodHookParam?) {
                    if (params?.args.isNullOrEmpty()) return
                    (params!!.args[0] as? Uri)?.showSinkDialog()
                }
            },
            object : HookMethodInfo {
                override val methodName: String = "delete"
                override fun beforeHook(context: Context?) {
                    //do nothing
                }
                override fun beforeHook(params: XC_MethodHook.MethodHookParam?) {
                    if (params?.args.isNullOrEmpty()) return
                    (params!!.args[0] as? Uri)?.showSinkDialog()
                }
            }
    )
}
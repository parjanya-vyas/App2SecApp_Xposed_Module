package com.parjanya.app2secapp.wrapper.source

import android.content.Context
import android.net.Uri
import com.parjanya.app2secapp.utils.*
import com.parjanya.app2secapp.wrapper.base.SecWrapper
import com.parjanya.app2secapp.wrapper.base.hook.HookMethodInfo
import de.robv.android.xposed.XC_MethodHook

object CursorLoaderWrapper : SecWrapper {
    override val packageName: String = "android.content"
    override val className: String = "CursorLoader"
    override val hooks: List<HookMethodInfo> = arrayListOf(
            object : HookMethodInfo {
                override val methodName: String = ""
                override fun isConstructor(): Boolean = true
                override fun beforeHook(context: Context?) {
                    //do nothing
                }

                override fun beforeHook(params: XC_MethodHook.MethodHookParam?) {
                    if (params?.args.isNullOrEmpty() || params!!.args.size <= 1) return
                    (params.args[1] as? Uri)?.addPermissionToDb()
                }
            }
    )
}
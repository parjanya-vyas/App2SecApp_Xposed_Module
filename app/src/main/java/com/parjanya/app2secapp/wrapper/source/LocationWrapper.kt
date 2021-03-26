package com.parjanya.app2secapp.wrapper.source

import com.parjanya.app2secapp.utils.Permission
import com.parjanya.app2secapp.wrapper.base.SecWrapper
import com.parjanya.app2secapp.wrapper.base.hook.HookMethodInfo
import com.parjanya.app2secapp.wrapper.base.hook.SourceHookMethodInfo

object LocationWrapper : SecWrapper {
    override val packageName: String = "android.location"
    override val className: String = "LocationManager"
    override val hooks: List<HookMethodInfo> = arrayListOf(
            SourceHookMethodInfo(
                    methodName = "getLastKnownLocation",
                    permissions = listOf(Permission.LOCATION)
            )
    )
}
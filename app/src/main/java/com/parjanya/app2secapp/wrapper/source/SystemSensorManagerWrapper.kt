package com.parjanya.app2secapp.wrapper.source

import com.parjanya.app2secapp.utils.Permission
import com.parjanya.app2secapp.wrapper.base.SecWrapper
import com.parjanya.app2secapp.wrapper.base.hook.HookMethodInfo
import com.parjanya.app2secapp.wrapper.base.hook.SourceHookMethodInfo

object SystemSensorManagerWrapper : SecWrapper {
    override val packageName: String = "android.hardware"
    override val className: String = "SystemSensorManager"
    override val hooks: List<HookMethodInfo> = arrayListOf(
            SourceHookMethodInfo(
                    methodName = "getDefaultSensor",
                    permissions = listOf(Permission.SENSOR)
            ),
            SourceHookMethodInfo(
                    methodName = "getDynamicSensorList",
                    permissions = listOf(Permission.SENSOR)
            ),
            SourceHookMethodInfo(
                    methodName = "getSensorList",
                    permissions = listOf(Permission.SENSOR)
            )
    )
}
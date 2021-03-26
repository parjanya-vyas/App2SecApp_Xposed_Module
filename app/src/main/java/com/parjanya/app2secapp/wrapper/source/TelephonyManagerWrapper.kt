package com.parjanya.app2secapp.wrapper.source

import com.parjanya.app2secapp.utils.Permission
import com.parjanya.app2secapp.wrapper.base.SecWrapper
import com.parjanya.app2secapp.wrapper.base.hook.HookMethodInfo
import com.parjanya.app2secapp.wrapper.base.hook.SourceHookMethodInfo

object TelephonyManagerWrapper : SecWrapper {
    override val packageName: String = "android.telephony"
    override val className: String = "TelephonyManager"
    override val hooks: List<HookMethodInfo> = arrayListOf(
            SourceHookMethodInfo(
                    methodName = "getAllCellInfo",
                    permissions = listOf(Permission.READ_PHONE_STATE)
            ),
            SourceHookMethodInfo(
                    methodName = "getCallState",
                    permissions = listOf(Permission.READ_PHONE_STATE)
            ),
            SourceHookMethodInfo(
                    methodName = "getCellLocation",
                    permissions = listOf(Permission.READ_PHONE_STATE, Permission.LOCATION)
            ),
            SourceHookMethodInfo(
                    methodName = "getDeviceId",
                    permissions = listOf(Permission.READ_PHONE_STATE)
            ),
            SourceHookMethodInfo(
                    methodName = "getImei",
                    permissions = listOf(Permission.READ_PHONE_STATE)
            ),
            SourceHookMethodInfo(
                    methodName = "getLine1Number",
                    permissions = listOf(Permission.READ_PHONE_STATE)
            ),
            SourceHookMethodInfo(
                    methodName = "getNetworkOperator",
                    permissions = listOf(Permission.READ_PHONE_STATE)
            ),
            SourceHookMethodInfo(
                    methodName = "getNetworkOperatorName",
                    permissions = listOf(Permission.READ_PHONE_STATE)
            ),
            SourceHookMethodInfo(
                    methodName = "getSimCarrierId",
                    permissions = listOf(Permission.READ_PHONE_STATE)
            ),
            SourceHookMethodInfo(
                    methodName = "getSimCarrierIdName",
                    permissions = listOf(Permission.READ_PHONE_STATE)
            ),
            SourceHookMethodInfo(
                    methodName = "getSimOperator",
                    permissions = listOf(Permission.READ_PHONE_STATE)
            ),
            SourceHookMethodInfo(
                    methodName = "getSimOperatorName",
                    permissions = listOf(Permission.READ_PHONE_STATE)
            ),
            SourceHookMethodInfo(
                    methodName = "getSimState",
                    permissions = listOf(Permission.READ_PHONE_STATE)
            ),
            SourceHookMethodInfo(
                    methodName = "getVoiceMailNumber",
                    permissions = listOf(Permission.READ_PHONE_STATE)
            ),
            SourceHookMethodInfo(
                    methodName = "isDataEnabled",
                    permissions = listOf(Permission.READ_PHONE_STATE)
            )
    )
}
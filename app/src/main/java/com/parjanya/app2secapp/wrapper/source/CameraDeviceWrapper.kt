package com.parjanya.app2secapp.wrapper.source

import com.parjanya.app2secapp.utils.Permission
import com.parjanya.app2secapp.wrapper.base.SecWrapper
import com.parjanya.app2secapp.wrapper.base.hook.HookMethodInfo
import com.parjanya.app2secapp.wrapper.base.hook.SourceHookMethodInfo

object CameraDeviceWrapper : SecWrapper {
    override val packageName: String = "android.hardware.camera2.impl"
    override val className: String = "CameraDeviceImpl"
    override val hooks: List<HookMethodInfo> = arrayListOf(
            SourceHookMethodInfo(
                    methodName = "createCaptureSession",
                    permissions = listOf(Permission.CAMERA)
            )
    )
}
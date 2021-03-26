package com.parjanya.app2secapp.wrapper.base

import com.parjanya.app2secapp.wrapper.both.ContentResolverWrapper
import com.parjanya.app2secapp.wrapper.both.IntentWrapper
import com.parjanya.app2secapp.wrapper.sink.ContextWrapper
import com.parjanya.app2secapp.wrapper.sink.EnvironmentWrapper
import com.parjanya.app2secapp.wrapper.sink.SMSManagerWrapper
import com.parjanya.app2secapp.wrapper.sink.URLConnectionWrapper
import com.parjanya.app2secapp.wrapper.source.*

object SecWrapperFactory {
    fun getWrapper(packageName: String): SecWrapper? {
        return when(packageName) {
            CallLogWrapper.packageName -> CallLogWrapper
            CameraDeviceWrapper.packageName -> CameraDeviceWrapper
            ContentResolverWrapper.packageName -> ContentResolverWrapper
            ContextWrapper.packageName -> ContextWrapper
            CursorLoaderWrapper.packageName -> CursorLoaderWrapper
            EnvironmentWrapper.packageName -> EnvironmentWrapper
            IntentWrapper.packageName -> IntentWrapper
            LocationWrapper.packageName -> LocationWrapper
            MediaRecorderWrapper.packageName -> MediaRecorderWrapper
            SystemSensorManagerWrapper.packageName -> SystemSensorManagerWrapper
            SMSManagerWrapper.packageName -> SMSManagerWrapper
            TelephonyManagerWrapper.packageName -> TelephonyManagerWrapper
            URLConnectionWrapper.packageName -> URLConnectionWrapper
            else -> null
        }
    }
}
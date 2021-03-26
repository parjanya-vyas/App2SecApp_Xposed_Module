package com.parjanya.app2secapp.utils

import android.content.Context
import android.net.Uri

fun Uri.addPermissionToDb(context: Context? = ActivityHook.currentActivity.get()) {
    val uriStr = toString()
    val permission = when {
        uriStr.contains("call_log/calls") -> Permission.READ_CALL_LOG
        uriStr.contains("com.android.contacts/data") -> Permission.READ_CONTACTS
        uriStr.contains("com.android.calendar") -> Permission.READ_CALENDAR
        else -> null
    }
    if (permission != null) DBHelper.getInstance(context).insertPermission(permission.toString())
}

fun Uri.showSinkDialog(context: Context? = ActivityHook.currentActivity.get()) {
    val uriStr = toString()
    val (msg, pos, neg) = when {
        uriStr.contains("call_log/calls") ->
            Triple(" It wants to insert a number to Call Log.", "Call Log influenced", "Call Log not influenced")
        uriStr.contains("com.android.contacts/data") ->
            Triple(" It wants to insert a Contact.", "Contacts influenced", "Contacts not influenced")
        uriStr.contains("com.android.calendar") ->
            Triple(" It wants to add an event to Calendar.", "Calendar influenced", "Calendar not influenced")
        else -> Triple("", "", "")
    }
    if (msg.isNotBlank()) {
        LeakAlert(
                messageFooter = msg,
                positiveToastText = pos,
                negativeToastText = neg,
                context = context
        ).show()
    }
}
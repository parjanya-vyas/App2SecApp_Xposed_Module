package com.parjanya.app2secapp.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

class LeakAlert(private val context: Context?,
        val messageFooter: String,
        val positiveToastText: String,
        val negativeToastText: String) {
    fun show() {
        val permissions = DBHelper.getInstance(context).getAllPermissions()
        val alert = AlertDialog.Builder(context)
        alert.setTitle("Are you sure?")
        alert.setMessage("The application has been influenced by following permissions recently:\n $permissions $messageFooter")
        alert.setPositiveButton("Continue") { _, _ ->
            Toast.makeText(context, positiveToastText, Toast.LENGTH_SHORT).show()
        }
        alert.setNegativeButton("No") { _, _ ->
            Toast.makeText(context, negativeToastText, Toast.LENGTH_SHORT).show()
            throw RuntimeException("Data leak denied")
        }
        alert.show()
    }
}
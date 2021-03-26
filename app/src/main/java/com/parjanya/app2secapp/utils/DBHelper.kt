package com.parjanya.app2secapp.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper private constructor(context: Context?)
    : SQLiteOpenHelper(context?.applicationContext, DATABASE_NAME, null, 1) {

    companion object {
        private const val DATABASE_NAME = "Permissions.db"
        private const val TABLE_NAME = "permissions_table"
        private const val COL_1 = "Permissions"

        private lateinit var instance: DBHelper

        fun getInstance(context: Context?): DBHelper {
            if (!::instance.isInitialized)
                instance = DBHelper(context)
            return instance
        }
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val createDbBuf = StringBuffer()
        createDbBuf.append("create table ")
        createDbBuf.append(TABLE_NAME)
        createDbBuf.append("( $COL_1 text UNIQUE)")
        sqLiteDatabase.execSQL(createDbBuf.toString())
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("drop table $TABLE_NAME")
        onCreate(sqLiteDatabase)
    }

    fun insertPermission(permission: String): Boolean {
        if (exists(permission)) {
            return false
        }
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, permission)
        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }

    fun getAllPermissions(): String {
        val db = this.writableDatabase
        val res: Cursor = db.rawQuery("select * from $TABLE_NAME", null)
        return if (res.count != 0) {
            val buffer = StringBuffer()
            while (res.moveToNext()) {
                if (!res.isLast) buffer.append(
                    res.getString(0).toString() + ", "
                ) else buffer.append(res.getString(0).toString() + ".")
            }
            buffer.toString()
        } else {
            "None."
        }
    }

    fun deletePermission(permission: String): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COL_1 = ?", arrayOf(permission))
    }

    private fun exists(permission: String?): Boolean {
        var exist = false
        val db = this.writableDatabase
        val res: Cursor = db.rawQuery("select * from $TABLE_NAME", null)
        if (res.getCount() !== 0) {
            while (res.moveToNext()) {
                if (res.getString(0).equals(permission)) {
                    exist = true
                    break
                }
            }
        }
        return exist
    }

}
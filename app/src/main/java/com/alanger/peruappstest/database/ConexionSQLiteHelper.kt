package com.alanger.peruappstest.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

import com.alanger.peruappstest.database.DataBaseDesign.CREATETABLE_COMENT
import com.alanger.peruappstest.database.DataBaseDesign.CREATETABLE_POST
import com.alanger.peruappstest.database.DataBaseDesign.TAB_COMENT
import com.alanger.peruappstest.database.DataBaseDesign.TAB_POST

class ConexionSQLiteHelper(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "onCreate")
        db.execSQL(CREATETABLE_POST)
        db.execSQL(CREATETABLE_COMENT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "onUpgrade: <old:$oldVersion> <new:$newVersion>")
        db.execSQL("DROP TABLE IF EXISTS $TAB_POST")
        db.execSQL("DROP TABLE IF EXISTS $TAB_COMENT")
        onCreate(db)
    }

    companion object {
        var VERSION_DB = 3
        private val TAG = "ConexionSQLiteHelper"
    }
}
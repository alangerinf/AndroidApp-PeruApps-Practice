package com.alanger.peruappstest.models.DAO

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import android.widget.Toast

import com.alanger.peruappstest.database.ConexionSQLiteHelper
import com.alanger.peruappstest.models.VO.PostVO

import java.util.ArrayList

import com.alanger.peruappstest.database.DataBaseDesign.DATABASE_NAME
import com.alanger.peruappstest.database.DataBaseDesign.TAB_POST
import com.alanger.peruappstest.database.DataBaseDesign.TAB_POST_BODY
import com.alanger.peruappstest.database.DataBaseDesign.TAB_POST_ID
import com.alanger.peruappstest.database.DataBaseDesign.TAB_POST_TITTLE
import com.alanger.peruappstest.database.DataBaseDesign.TAB_POST_USERID
import com.alanger.peruappstest.database.DataBaseDesign._FROM
import com.alanger.peruappstest.database.DataBaseDesign._SELECT
import com.alanger.peruappstest.database.DataBaseDesign._WHERE

class PostDAO(private val ctx: Context) {

    fun dropTable(): Boolean {
        var flag = false
        val conn = ConexionSQLiteHelper(ctx, DATABASE_NAME, null, ConexionSQLiteHelper.VERSION_DB)
        val db = conn.writableDatabase
        val res = db.delete(TAB_POST, null, null)
        if (res > 0) {
            flag = true
        }
        db.close()
        conn.close()
        return flag
    }

    fun insert(postVO: PostVO): Long {
        val conn = ConexionSQLiteHelper(ctx, DATABASE_NAME, null, ConexionSQLiteHelper.VERSION_DB)
        val db = conn.writableDatabase
        val values = ContentValues()
        values.put(TAB_POST_ID, postVO.id)
        values.put(TAB_POST_USERID, postVO.userId)
        values.put(TAB_POST_TITTLE, postVO.title)
        values.put(TAB_POST_BODY, postVO.body)
        val temp = db.insert(TAB_POST, TAB_POST_ID, values)
        db.close()
        conn.close()
        return temp
    }

    fun delete(postVO: PostVO): Long {
        val conn = ConexionSQLiteHelper(ctx, DATABASE_NAME, null, ConexionSQLiteHelper.VERSION_DB)
        val db = conn.writableDatabase
        val args = arrayOf(postVO.id.toString())
        val temp = db.delete(TAB_POST, "$TAB_POST_ID=?", args).toLong()
        db.close()
        conn.close()
        return temp
    }

    fun selectById(id: Int): PostVO? {
        val c: ConexionSQLiteHelper
        c = ConexionSQLiteHelper(ctx, DATABASE_NAME, null, ConexionSQLiteHelper.VERSION_DB)
        val db = c.readableDatabase
        var temp: PostVO? = null
        try {

            val cursor = db.rawQuery(
                    _SELECT +
                            "*" +
                            _FROM +
                            TAB_POST + " as P" +
                            _WHERE +
                            "P." + TAB_POST_ID + " = " + id, null)
            if (cursor.count > 0) {
                cursor.moveToFirst()
                temp = getAtributtes(cursor)
            }
            cursor.close()
        } catch (e: Exception) {
            Toast.makeText(ctx, "$TAG selectById $e", Toast.LENGTH_SHORT).show()
            Log.d(TAG, " selectById $e")
        } finally {
            db.close()
            c.close()
        }
        return temp
    }

    fun listAll(): List<PostVO> {
        val c: ConexionSQLiteHelper
        c = ConexionSQLiteHelper(ctx, DATABASE_NAME, null, ConexionSQLiteHelper.VERSION_DB)
        val db = c.readableDatabase
        val salidaVOList = ArrayList<PostVO>()
        try {

            val cursor = db.query(TAB_POST, null, null, null, null, null, null)
            while (cursor.moveToNext()) {
                val temp = getAtributtes(cursor)
                salidaVOList.add(temp)
            }
            cursor.close()
        } catch (e: Exception) {
            Toast.makeText(ctx, "$TAG listAll $e", Toast.LENGTH_SHORT).show()
            Log.d(TAG, " listAll $e")
        } finally {
            db.close()
            c.close()
        }
        return salidaVOList
    }

    private fun getAtributtes(cursor: Cursor): PostVO {
        val temp = PostVO()
        val columnNames = cursor.columnNames
        for (name in columnNames) {
            when (name) {
                TAB_POST_ID -> temp.id = cursor.getInt(cursor.getColumnIndex(name))
                TAB_POST_USERID -> temp.userId = cursor.getInt(cursor.getColumnIndex(name))
                TAB_POST_TITTLE -> temp.title = cursor.getString(cursor.getColumnIndex(name))
                TAB_POST_BODY -> temp.body = cursor.getString(cursor.getColumnIndex(name))
            }
            temp.isSaved = true
            temp.comentVOList = ComentDAO(ctx).listById(temp)
        }


        return temp
    }

    companion object {
        private val TAG = PostDAO::class.java.simpleName
    }


}

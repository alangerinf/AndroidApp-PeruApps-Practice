package com.alanger.peruappstest.models.DAO

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import android.widget.Toast

import com.alanger.peruappstest.Utils
import com.alanger.peruappstest.database.ConexionSQLiteHelper
import com.alanger.peruappstest.models.VO.ComentVO
import com.alanger.peruappstest.models.VO.PostVO

import java.util.ArrayList

import com.alanger.peruappstest.database.DataBaseDesign.DATABASE_NAME
import com.alanger.peruappstest.database.DataBaseDesign.TAB_COMENT
import com.alanger.peruappstest.database.DataBaseDesign.TAB_COMENT_BODY
import com.alanger.peruappstest.database.DataBaseDesign.TAB_COMENT_DATETIME
import com.alanger.peruappstest.database.DataBaseDesign.TAB_COMENT_ID
import com.alanger.peruappstest.database.DataBaseDesign.TAB_COMENT_IDPOST
import com.alanger.peruappstest.database.DataBaseDesign._FROM
import com.alanger.peruappstest.database.DataBaseDesign._SELECT
import com.alanger.peruappstest.database.DataBaseDesign._WHERE

class ComentDAO(private val ctx: Context) {

    fun dropTable(): Boolean {
        var flag = false
        val conn = ConexionSQLiteHelper(ctx, DATABASE_NAME, null, ConexionSQLiteHelper.VERSION_DB)
        val db = conn.writableDatabase
        val res = db.delete(TAB_COMENT, null, null)
        if (res > 0) {
            flag = true
        }
        db.close()
        conn.close()
        return flag
    }

    fun insert(comentVO: ComentVO): Long {
        val conn = ConexionSQLiteHelper(ctx, DATABASE_NAME, null, ConexionSQLiteHelper.VERSION_DB)
        val db = conn.writableDatabase
        val values = ContentValues()
        values.put(TAB_COMENT_BODY, comentVO.body)
        values.put(TAB_COMENT_IDPOST, comentVO.idPost)
        val temp = db.insert(TAB_COMENT, TAB_COMENT_ID, values)
        db.close()
        conn.close()
        return temp
    }

    fun update(comentVO: ComentVO): Long {
        val conn = ConexionSQLiteHelper(ctx, DATABASE_NAME, null, ConexionSQLiteHelper.VERSION_DB)
        val db = conn.writableDatabase
        val values = ContentValues()
        values.put(TAB_COMENT_BODY, comentVO.body)
        values.put(TAB_COMENT_DATETIME, Utils.getCurrentDateTime())

        val args = arrayOf(comentVO.id.toString())
        values.put(TAB_COMENT_BODY, comentVO.body)
        val temp = db.update(TAB_COMENT, values, "$TAB_COMENT_ID=?", args).toLong()
        db.close()
        conn.close()
        return temp
    }

    fun selectById(id: Int): ComentVO? {
        val c: ConexionSQLiteHelper
        c = ConexionSQLiteHelper(ctx, DATABASE_NAME, null, ConexionSQLiteHelper.VERSION_DB)
        val db = c.readableDatabase
        var temp: ComentVO? = null
        try {

            val cursor = db.rawQuery(
                    _SELECT +
                            "*" +
                            _FROM +
                            TAB_COMENT + " as C" +
                            _WHERE +
                            "C." + TAB_COMENT_ID + " = " + id, null)
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


    fun listById(post: PostVO): List<ComentVO> {
        return listById(post.id)
    }

    fun listById(id: Int): List<ComentVO> {
        val c: ConexionSQLiteHelper
        c = ConexionSQLiteHelper(ctx, DATABASE_NAME, null, ConexionSQLiteHelper.VERSION_DB)
        val db = c.readableDatabase
        val comentVOList = ArrayList<ComentVO>()
        try {

            val args = arrayOf(id.toString())

            val cursor = db.query(TAB_COMENT, null, "$TAB_COMENT_IDPOST=?", args, null, null, null)
            while (cursor.moveToNext()) {
                val temp = getAtributtes(cursor)
                comentVOList.add(temp)
            }
            cursor.close()
        } catch (e: Exception) {
            Toast.makeText(ctx, "$TAG listAll $e", Toast.LENGTH_SHORT).show()
            Log.d(TAG, " listAll $e")
        } finally {
            db.close()
            c.close()
        }
        return comentVOList
    }

    private fun getAtributtes(cursor: Cursor): ComentVO {
        val temp = ComentVO()
        val columnNames = cursor.columnNames
        for (name in columnNames) {
            when (name) {
                TAB_COMENT_ID -> temp.id = cursor.getInt(cursor.getColumnIndex(name))
                TAB_COMENT_IDPOST -> temp.id = cursor.getInt(cursor.getColumnIndex(name))
                TAB_COMENT_BODY -> temp.body = cursor.getString(cursor.getColumnIndex(name))
                TAB_COMENT_DATETIME -> temp.dateTime = cursor.getString(cursor.getColumnIndex(name))
            }
        }

        return temp
    }

    companion object {
        private val TAG = ComentDAO::class.java.simpleName
    }


}

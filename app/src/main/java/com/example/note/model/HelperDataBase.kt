package com.example.note.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.note.MainContract

class HelperDataBase(val c:Context):SQLiteOpenHelper(c,DataBaseInfo.nameBd,null,DataBaseInfo.versionBd),MainContract.MainHelper {
    override fun onCreate(db: SQLiteDatabase?) {
        val create="CREATE TABLE ${DataBaseInfo.table_name} ("+
                "${DataBaseInfo.columnId} INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "${DataBaseInfo.columnTitle} TEXT ,"+
                "${DataBaseInfo.columnText} TEXT )"
        db?.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    override fun readFromDb():MutableList<datas> {
        val r=readableDatabase
        val current=r.rawQuery("SELECT * FROM ${DataBaseInfo.table_name}",null)
        val l:MutableList<datas> =mutableListOf()
        while (current.moveToNext()){
            val title=current.getString(current.getColumnIndexOrThrow(DataBaseInfo.columnTitle))
            val text=current.getString(current.getColumnIndexOrThrow(DataBaseInfo.columnText))
            val id=current.getInt(current.getColumnIndexOrThrow(DataBaseInfo.columnId))
            l.add(datas(title,text,id))
        }
        r.close()
        current.close()
        return l
    }

    override fun addFromDb(contentValues: ContentValues):Boolean {
        val w=writableDatabase
        val r=w.insert(DataBaseInfo.table_name,null,contentValues)
        w.close()
        if (r==-1L){
            return false
        }
        else {
            return true
        }
    }

    override fun Validation_add(content: ContentValues,id:Int?):Int {
        val w=writableDatabase
        val res=w.update(DataBaseInfo.table_name,content,"$id = ${DataBaseInfo.columnId}",null)
        w.close()
        return res
    }
    override fun delete_element(id:Int?){
        val r=readableDatabase
        r.delete(DataBaseInfo.table_name,"$id=${DataBaseInfo.columnId}",null)
        r.close()
    }

}
package com.example.note.presenter

import android.content.ContentValues
import android.content.Context
import com.example.note.MainContract
import com.example.note.model.DataBaseInfo
import com.example.note.model.HelperDataBase
import com.example.note.model.datas
import com.example.note.verif_info
import com.example.note.view.WriteNotesActivity

class Presenter_write(val c:Context, val cw: MainContract.MainWrite):MainContract.MainPresenterWrite {
    val db=HelperDataBase(c)
    override fun Add(data: datas){
        if (verif_info(data)){
            val content=ContentValues().apply {
                put(DataBaseInfo.columnTitle,data.title)
                put(DataBaseInfo.columnText,data.text)
            }
            val add_Result=db.addFromDb(content)
            cw.result(add_Result)
        }

    }
}
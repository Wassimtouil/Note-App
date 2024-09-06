package com.example.note.presenter

import android.content.ContentValues
import android.content.Context
import com.example.note.MainContract
import com.example.note.model.DataBaseInfo
import com.example.note.model.HelperDataBase
import com.example.note.model.datas
import com.example.note.verif_info

class Presenter_update(val c:Context,val cu:MainContract.MainUpdate):MainContract.MainPresenterUpdate {
    val db=HelperDataBase(c)
    override fun valid(data: datas){
        if (verif_info(data)){
            val content=ContentValues().apply {
                put(DataBaseInfo.columnText,data.text)
                put(DataBaseInfo.columnTitle,data.title)
                put(DataBaseInfo.columnId,data.id)
            }
            val result=db.Validation_add(content,data.id)
            if (result>0){
                cu.result(true)
            }
            else {
                cu.result(false)
            }
        }

    }
}
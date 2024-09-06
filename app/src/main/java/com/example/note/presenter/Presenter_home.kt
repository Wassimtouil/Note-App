package com.example.note.presenter

import android.content.Context
import com.example.note.MainContract
import com.example.note.model.HelperDataBase

class Presenter_home(val c:Context, val cHome:MainContract.MainHome):MainContract.MainPresenterHome {
    val db=HelperDataBase(c)
    override fun show() {
        val res=db.readFromDb()
        cHome.result(res)
    }
}
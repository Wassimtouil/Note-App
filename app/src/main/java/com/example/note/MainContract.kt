package com.example.note

import android.content.ContentValues
import com.example.note.model.datas


interface MainContract {
    //Presenter
    interface MainPresenterHome{
        fun show()
    }
    interface MainPresenterWrite{
        fun Add(data: datas)
    }
    interface MainPresenterUpdate{
        fun valid(data: datas)
    }
    //Activity
    interface MainHome{
        fun result(l:MutableList<datas>)
    }
    interface MainWrite{
        fun result(b:Boolean)
    }
    interface MainUpdate {
        fun result(m:Boolean)
    }
    //Helper
    interface MainHelper{
        fun readFromDb():MutableList<datas>
        fun addFromDb(content:ContentValues):Boolean
        fun Validation_add(content: ContentValues,id:Int?):Int
        fun delete_element(id:Int?)
    }


}
package com.example.note

import com.example.note.model.datas

fun verif_info(data:datas):Boolean{
    return  (data.title.isNotEmpty() && data.text.isNotEmpty())
}
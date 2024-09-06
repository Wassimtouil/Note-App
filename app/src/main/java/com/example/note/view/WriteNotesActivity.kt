package com.example.note.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.note.MainContract
import com.example.note.databinding.ActivityWriteNotesBinding
import com.example.note.model.datas
import com.example.note.presenter.Presenter_write

class WriteNotesActivity : AppCompatActivity(),MainContract.MainWrite {
    lateinit var binding:ActivityWriteNotesBinding
    lateinit var p:Presenter_write
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityWriteNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        p=Presenter_write(this,this)
        binding.save.setOnClickListener {
            p.Add(datas(binding.titleNote.text.toString(),binding.descNote.text.toString(),null))
        }

    }

    override fun result(b: Boolean) {
        if (b){
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }
}
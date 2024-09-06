package com.example.note.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.note.MainContract
import com.example.note.R
import com.example.note.databinding.ActivityUpdateNoteBinding
import com.example.note.model.DataBaseInfo
import com.example.note.model.datas
import com.example.note.presenter.Presenter_update
import java.net.IDN

class UpdateNote : AppCompatActivity(),MainContract.MainUpdate {
    lateinit var binding:ActivityUpdateNoteBinding
    lateinit var p:Presenter_update
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        p=Presenter_update(this,this)
        var id=0
        intent.apply {
            binding.descNote.setText(getStringExtra(DataBaseInfo.columnText))
            binding.titleNote.setText(getStringExtra(DataBaseInfo.columnTitle))
            id=getIntExtra(DataBaseInfo.columnId,0)
        }
        binding.save.setOnClickListener {
            p.valid(datas(binding.titleNote.text.toString(),binding.descNote.text.toString(),id))
        }
    }

    override fun result(m:Boolean) {
        if (m){
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }
}
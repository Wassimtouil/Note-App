package com.example.note.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note.AdapterList
import com.example.note.MainContract
import com.example.note.databinding.ActivityHomeBinding
import com.example.note.model.datas
import com.example.note.presenter.Presenter_home

class HomeActivity : AppCompatActivity(),MainContract.MainHome {
    lateinit var binding: ActivityHomeBinding
    lateinit var p:Presenter_home
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackClick()
        p=Presenter_home(this,this)
        p.show()
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this,WriteNotesActivity::class.java))
        }
    }
    private fun onBackClick(){
        onBackPressedDispatcher.addCallback(this,object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })
    }
    override fun result(l: MutableList<datas>) {
        if (l.isNotEmpty()){
            val r=binding.recycler
            r.setHasFixedSize(false)
            r.layoutManager=LinearLayoutManager(this)
            val Adapter=AdapterList(this,l)
            r.adapter=Adapter
        }
    }
    override fun onResume() {
        super.onResume()
        p.show()
    }


}
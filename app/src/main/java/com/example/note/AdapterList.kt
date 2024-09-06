package com.example.note

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.note.model.DataBaseInfo
import com.example.note.model.HelperDataBase
import com.example.note.model.datas
import com.example.note.view.UpdateNote

class AdapterList(val c:Context,val list:MutableList<datas>): RecyclerView.Adapter<AdapterList.ViewHolder>()
{
    val db=HelperDataBase(c)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterList.ViewHolder {
        val item=LayoutInflater.from(parent.context).inflate(R.layout.element,parent,false)
        return ViewHolder(item)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title:TextView =itemView.findViewById(R.id.title_element)
        val edit:ImageView =itemView.findViewById(R.id.edit_element)
        val delete:ImageView =itemView.findViewById(R.id.delete_element)
    }

    override fun onBindViewHolder(holder: AdapterList.ViewHolder, position: Int) {
        val element=list[position]
        holder.title.text=element.title
        holder.edit.setOnClickListener {
            val i=Intent(holder.itemView.context,UpdateNote::class.java).apply {
                putExtra(DataBaseInfo.columnText,element.text)
                putExtra(DataBaseInfo.columnTitle,element.title)
                putExtra(DataBaseInfo.columnId,element.id)
            }
            holder.itemView.context.startActivity(i)
        }
        holder.delete.setOnClickListener {
            db.delete_element(element.id)
            list.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,list.size)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
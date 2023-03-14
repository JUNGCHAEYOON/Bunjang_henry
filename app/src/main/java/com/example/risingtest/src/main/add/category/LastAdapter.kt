package com.example.risingtest.src.main.add.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.risingtest.R

class LastAdapter(val pass2Interface: Pass2Interface, val itemList:ArrayList<CategoryItem>) : RecyclerView.Adapter<LastAdapter.LastViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categoryfrag,parent,false)
        return LastViewHolder(view)
    }

    override fun onBindViewHolder(holder: LastViewHolder, position: Int) {
        holder.categoryfrag_categorytitle.text = itemList[position].title

        holder.itemView.setOnClickListener {
            pass2Interface.onPass2CategoryId(itemList[position].categoryId)
            pass2Interface.onPass2CategoryTitle(itemList[position].title)
        }
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    inner class LastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val categoryfrag_categorytitle = itemView.findViewById<TextView>(R.id.categoryfrag_categorytitle)
    }
}

package com.example.risingtest.src.main.add.tag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.risingtest.R

class TagAdapter(val tagPassInterface: TagPassInterface ,val itemList:ArrayList<TagItem>) : RecyclerView.Adapter<TagAdapter.TagViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tag_rv,parent,false)
        return TagViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.tag_tv_item.text = itemList[position].name

        holder.itemView.setOnClickListener {
            tagPassInterface.onPassTag(itemList[position].name)
            tagPassInterface.onPassTagId(itemList[position].id)
        }
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    inner class TagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tag_tv_item = itemView.findViewById<TextView>(R.id.tag_tv_item)

    }
}
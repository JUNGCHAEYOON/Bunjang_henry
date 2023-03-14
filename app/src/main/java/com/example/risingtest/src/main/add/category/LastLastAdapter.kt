package com.example.risingtest.src.main.add.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.risingtest.R

class LastLastAdapter(val pass3Interface: Pass3Interface, val itemList:ArrayList<CategoryItem>) : RecyclerView.Adapter<LastLastAdapter.LastLastViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastLastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categoryfrag,parent,false)
        return LastLastViewHolder(view)
    }

    override fun onBindViewHolder(holder: LastLastViewHolder, position: Int) {
        holder.categoryfrag_categorytitle.text = itemList[position].title

        holder.itemView.setOnClickListener {
            pass3Interface.onPass3CategoryId(itemList[position].categoryId)
            pass3Interface.onPass3CategoryTitle(itemList[position].title)
        }
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    inner class LastLastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val categoryfrag_categorytitle = itemView.findViewById<TextView>(R.id.categoryfrag_categorytitle)
    }
}

package com.example.risingtest.src.main.add.addrv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.risingtest.R

class addRecyclerAdapter (val itemList:ArrayList<addItem>) : RecyclerView.Adapter<addRecyclerAdapter.addRecyclerViewHolder>(){
    inner class addRecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val add_iv_rv_item = itemView.findViewById<ImageView>(R.id.add_iv_rv_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): addRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_add_rv,parent,false)
        return addRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: addRecyclerViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(itemList[position].image).transform(CenterCrop(), RoundedCorners(5)).into(holder.add_iv_rv_item)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }
}
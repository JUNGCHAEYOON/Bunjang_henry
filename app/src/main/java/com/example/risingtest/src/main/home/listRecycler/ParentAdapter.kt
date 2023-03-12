package com.example.risingtest.src.main.home.listRecycler

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.risingtest.R
import com.example.risingtest.src.main.home.listdomain.ListDomainActivity

class ParentAdapter (val itemList:ArrayList<ParentItem>) : RecyclerView.Adapter<ParentAdapter.ParentViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_list_parent,parent,false)
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        // 타이튼
        holder.homelist_tv_parenttitle.text = itemList[position].title

        // 맞는지 모르겠음 튕길수도?, 리싸이클러 연결
        holder.homelist_rv.adapter = itemList[position].recyclerAdapter
        holder.homelist_rv.layoutManager = GridLayoutManager(holder.itemView.context,3)

        // 리스트도메인 연결
        holder.homelist_btn_listdomain.setOnClickListener {
            val intent = Intent(holder.itemView.context, ListDomainActivity::class.java)
            intent.putExtra("title",itemList[position].title)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val homelist_rv = itemView.findViewById<RecyclerView>(R.id.homelist_rv)
        val homelist_tv_parenttitle = itemView.findViewById<TextView>(R.id.homelist_tv_parenttitle)
        val homelist_btn_listdomain = itemView.findViewById<TextView>(R.id.homelist_btn_listdomain)
    }
}
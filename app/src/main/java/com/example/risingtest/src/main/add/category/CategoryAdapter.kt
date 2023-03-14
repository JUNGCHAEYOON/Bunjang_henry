package com.example.risingtest.src.main.add.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.risingtest.R

class CategoryAdapter(val passInterface: PassInterface,val itemList:ArrayList<CategoryItem>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categoryfrag,parent,false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.categoryfrag_categorytitle.text = itemList[position].title

        holder.itemView.setOnClickListener {
//            Toast.makeText(holder.itemView.context, itemList[position].categoryId.toString(), Toast.LENGTH_SHORT).show()
            passInterface.onPassCategoryId(itemList[position].categoryId)
            passInterface.onPassCategoryTitle(itemList[position].title)
        }
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val categoryfrag_categorytitle = itemView.findViewById<TextView>(R.id.categoryfrag_categorytitle)
    }
}

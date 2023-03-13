package com.example.risingtest.src.main.home.listRecycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.risingtest.R
import com.example.risingtest.src.main.home.itemdomain.ItemDomainActivity
import java.text.DecimalFormat

class RecyclerAdapter(val itemList: ArrayList<RecyclerItem>) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_list, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        // 전체 아이템 클릭시
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ItemDomainActivity::class.java)
            intent.putExtra("id",itemList[position].id)
            intent.putExtra("userId",itemList[position].userId)
            holder.itemView.context.startActivity(intent)
        }

        // 이미지뷰
        Glide.with(holder.itemView.context).load(itemList[position].url).transform(CenterCrop(), RoundedCorners(10)).into(holder.homelist_iv_image)

        // 하트
        val iv_heart = holder.homelist_iv_heart
        iv_heart.setImageResource(R.drawable.ic_action_whiteheart)
        iv_heart.setTag("false")
        iv_heart.setOnClickListener {
            when (iv_heart.getTag()) {
                "false" -> {
                    iv_heart.setImageResource(R.drawable.ic_action_redheart)
                    iv_heart.setTag("true")
                }
                else -> {
                    iv_heart.setImageResource(R.drawable.ic_action_whiteheart)
                    iv_heart.setTag("false")
                }

            }
        }

        // 번개페이
        if (itemList[position].safePay == true){
            holder.homelist_iv_bungaepay.visibility = View.VISIBLE
        }else{
            holder.homelist_iv_bungaepay.visibility = View.INVISIBLE
        }

        // 가격
        val dec = DecimalFormat("#,###")
        val buff = dec.format(itemList[position].price)
        holder.homelist_tv_price.text = buff.toString() + "원"

        // 제목
        holder.homelist_tv_title.text = itemList[position].title
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val homelist_iv_image = itemView.findViewById<ImageView>(R.id.ld_iv_image)
        val homelist_iv_heart = itemView.findViewById<ImageView>(R.id.homelist_iv_heart)
        val homelist_iv_bungaepay = itemView.findViewById<ImageView>(R.id.ld_iv_heart)
        val homelist_tv_price = itemView.findViewById<TextView>(R.id.ld_tv_price)
        val homelist_tv_title = itemView.findViewById<TextView>(R.id.homelist_tv_title)
    }
}
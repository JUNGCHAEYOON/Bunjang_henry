package com.example.risingtest.src.main.home.itemdomain

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.risingtest.databinding.ViewPagerItemDomainBinding

class ItemDomainAdapter(context : Context, private val arrayList : ArrayList<String>) : PagerAdapter() {

    private lateinit var binding : ViewPagerItemDomainBinding
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        binding = ViewPagerItemDomainBinding.inflate(inflater, container, false)
        Glide.with(container.context).load(arrayList[position]).into(binding.itemdomainIvViewpagerimage)
        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view==`object`)
    }
}
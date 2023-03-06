package com.example.risingtest.src.main.myPage.mybungae.panmae

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.risingtest.src.main.myPage.mybungae.panmae.panmaeVp2.*

class PanmaePagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 6

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> PmJunFragment()
            1 -> PmPanmaejungFragment()
            2 -> PmYeyakFragment()
            3 -> PmWanryoFragment()
            4 -> PmGwanggoFragment()
            else -> PmPayFragment()
        }
    }
}
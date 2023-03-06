package com.example.risingtest.src.main.myPage.bonusbtn

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BonusPageAdapter(private val fragmentActivity: FragmentActivity,
                       private val fragments: List<Fragment>): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0 -> return Bonus1Fragment()
            1 -> return Bonus2Fragment()
            else -> return Bonus3Fragment()
        }
    }
}
package com.example.risingtest.src.main.myPage.mybungae.panmae

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.example.risingtest.R
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.FragmentPanmaeBinding
import com.example.risingtest.src.main.myPage.mybungae.jjim.JjimActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PanmaeFragment : BaseFragment<FragmentPanmaeBinding>(FragmentPanmaeBinding::bind, R.layout.fragment_panmae) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*탭레이아웃 */
        panmaeTl()
    }

    /* 탭 레이아웃 */
    private fun panmaeTl(){
        binding.panmaeVp2.adapter = PanmaePagerAdapter(requireActivity())
        val tabTextList = listOf<String>("전체","판매중","예약중","판매완료","광고중","페이가능")

        TabLayoutMediator(binding.panmaeTl,binding.panmaeVp2){ tab,position->
            tab.text = tabTextList[position]
        }.attach()

        setTabItemMargin(binding.panmaeTl,30)

        binding.panmaeTl.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0->{
                    }
                    1->{

                    }
                    2->{

                    }
                    3->{

                    }
                    4->{

                    }
                    else->{

                    }
                }
            }
        })
    }

    // TabLayout Tab 사이 간격 부여
    private fun setTabItemMargin(tabLayout: TabLayout, marginEnd: Int = 20) {
        for(i in 0 until 3) {
            val tabs = tabLayout.getChildAt(0) as ViewGroup
            for(i in 0 until tabs.childCount) {
                val tab = tabs.getChildAt(i)
                val lp = tab.layoutParams as LinearLayout.LayoutParams
                lp.marginEnd = marginEnd
                tab.layoutParams = lp
                tabLayout.requestLayout()
            }
        }
    }
}
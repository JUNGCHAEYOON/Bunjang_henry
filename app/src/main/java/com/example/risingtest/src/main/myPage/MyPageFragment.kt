package com.example.risingtest.src.main.myPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.risingtest.R
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.FragmentMyPageBinding
import com.example.risingtest.src.main.alim.AlimActivity
import com.example.risingtest.src.main.myPage.bonusbtn.Bonus1Fragment
import com.example.risingtest.src.main.myPage.bonusbtn.Bonus2Fragment
import com.example.risingtest.src.main.myPage.bonusbtn.Bonus3Fragment
import com.example.risingtest.src.main.myPage.bonusbtn.BonusPageAdapter
import com.example.risingtest.src.main.myPage.mybungae.MyBungaePagerFragmentStateAdapter
import com.example.risingtest.src.main.myPage.mybungae.jjim.JjimActivity
import com.example.risingtest.src.main.myPage.mybungae.panmae.HugiFragment
import com.example.risingtest.src.main.myPage.mybungae.panmae.PanmaeFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MyPageFragment :
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* 툴바 버튼 */
        // 알림 액티비티로 이동
        binding.mypageBtnBell.setOnClickListener {
            val intent = Intent(context, AlimActivity::class.java)
            startActivity(intent)
        }

        /* 동전 스핀 */
        val myPageIvCoin = binding.mypageIvCoin
        Glide.with(view.context).load(R.drawable.ic_coin_animation).into(myPageIvCoin)

        /* 보너스 버튼 뷰페이저 */
        bonusButtonViewPager()

        /* 나의번개 탭레이아웃 뷰페이저 */
        mybungaeTablayout()
    }

    /* 보너스 버튼 뷰페이저 */
    fun bonusButtonViewPager(){
        val mypageVp2Bonus = binding.mypageVp2Bonus
        // 프래그먼트 리스트
        val frag1 = Bonus1Fragment()
        val frag2 = Bonus2Fragment()
        val frag3 = Bonus3Fragment()
        val frags = ArrayList<Fragment>()
        frags.add(frag1)
        frags.add(frag2)
        frags.add(frag3)
        // adapter
        val adapter = BonusPageAdapter(requireActivity(),frags)
        mypageVp2Bonus.adapter = adapter
    }

    /* 나의번개 탭레이아웃 뷰페이저 */
    fun mybungaeTablayout(){
        val mypageTl = binding.mypageTl
        val mypageVp2Mybungae = binding.mypageVp2Mybungae
        val mybungaePagerAdapter = MyBungaePagerFragmentStateAdapter(requireActivity())

        mybungaePagerAdapter.addFragment(PanmaeFragment())
        mybungaePagerAdapter.addFragment(HugiFragment())

        //adapter 연결
        mypageVp2Mybungae?.adapter = mybungaePagerAdapter
        mypageVp2Mybungae?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })

        // tablayout attach
        TabLayoutMediator(mypageTl, mypageVp2Mybungae){ tab, position ->
            when(position){
                0 ->{
                    tab.text = "판매상품"
                }
                1 ->{
                    tab.text = "상점후기"
                }
            }
        }.attach()
        mypageTl.addTab(mypageTl.newTab().setText("찜목록"))
        mypageTl.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    2 -> {
                        val intent = Intent(activity,JjimActivity::class.java)
                        startActivity(intent)
                    }

                }
            }
        })
    }

}
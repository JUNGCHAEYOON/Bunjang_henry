package com.example.risingtest.src.main.myPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.risingtest.R
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.FragmentMyPageBinding
import com.example.risingtest.src.main.alim.AlimActivity

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
    }
}
package com.example.risingtest.src.main.home
import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.risingtest.R
import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.FragmentHomeBinding
import com.example.risingtest.src.main.search.SearchActivity
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs
import kotlin.math.min

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 콜랩싱 툴바 스크롤
        toolbarScroll()

        // 검색화면으로 이동
        binding.homeBtnSearch.setOnClickListener {
            val intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    // 콜랩싱 툴바 스크롤시
    fun toolbarScroll(){
        binding.homeAb.addOnOffsetChangedListener(object:
        AppBarLayout.OnOffsetChangedListener{
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                val alpha = min(abs(verticalOffset/2),255)
                binding.homeTbShrink.setBackgroundColor(Color.argb(alpha,255,255,255))
                if(alpha > 255/2){
                    binding.homeBtnMenu.setColorFilter(Color.argb(alpha,0,0,0))
                    binding.homeBtnSearch.setColorFilter(Color.argb(alpha,0,0,0))
                    binding.homeBtnAdd.setColorFilter(Color.argb(alpha,0,0,0))
                }else{
                    binding.homeBtnMenu.setColorFilter(Color.argb(alpha,255,255,255))
                    binding.homeBtnSearch.setColorFilter(Color.argb(alpha,255,255,255))
                    binding.homeBtnAdd.setColorFilter(Color.argb(alpha,255,255,255))
                }
            }

        })
    }
}
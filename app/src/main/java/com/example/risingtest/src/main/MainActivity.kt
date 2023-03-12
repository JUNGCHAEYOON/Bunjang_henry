package com.example.risingtest.src.main

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.view.WindowCompat
import com.example.risingtest.R
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityMainBinding
import com.example.risingtest.src.main.add.AddActivity
import com.example.risingtest.src.main.bungaetalk.BungaetalkFragment
import com.example.risingtest.src.main.home.HomeFragment
import com.example.risingtest.src.main.myPage.MyPageFragment
import com.example.risingtest.src.main.search.SearchActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()

        // search버튼, add버튼의 경우 액티비티로 이동!
        val intentSearch = Intent(this, SearchActivity::class.java)
        val intentAdd = Intent(this, AddActivity::class.java)
        
        // 바텀네비 선언
        binding.mainBtmNav.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_main_btm_nav_search -> {
                        startActivity(intentSearch)
                        binding.mainBtmNav.selectedItemId = R.id.menu_main_btm_nav_home
                    }
                    R.id.menu_main_btm_nav_add -> {
                        startActivity(intentAdd)
                        binding.mainBtmNav.selectedItemId = R.id.menu_main_btm_nav_home
                    }
                    R.id.menu_main_btm_nav_bungaetalk -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, BungaetalkFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_main_btm_nav_my_page -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, MyPageFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.menu_main_btm_nav_home
        }
    }
}
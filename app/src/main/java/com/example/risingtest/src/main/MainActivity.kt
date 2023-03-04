package com.example.risingtest.src.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.risingtest.R
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityMainBinding
import com.example.risingtest.src.main.add.AddFragment
import com.example.risingtest.src.main.bungaetalk.BungaetalkFragment
import com.example.risingtest.src.main.home.HomeFragment
import com.example.risingtest.src.main.myPage.MyPageFragment
import com.example.risingtest.src.main.search.SearchFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()

        binding.mainBtmNav.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_main_btm_nav_search -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, SearchFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_main_btm_nav_add -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, AddFragment())
                            .commitAllowingStateLoss()
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
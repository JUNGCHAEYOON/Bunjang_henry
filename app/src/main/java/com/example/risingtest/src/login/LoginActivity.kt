package com.example.risingtest.src.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityLoginBinding
import com.example.risingtest.src.login.bottomSheet.BottomSheet
import com.example.risingtest.src.login.viewPager2.LoginVP1Fragment
import com.example.risingtest.src.login.viewPager2.LoginVP2Fragment
import com.example.risingtest.src.login.viewPager2.LoginVP3Fragment
import com.example.risingtest.src.login.viewPager2.LoginPageAdapter

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private var currentPosition = 0
    private lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        /* 뷰페이저, 도트 인디케이터 연결 */

        // 프래그먼트 선언
        val vp1fragment = LoginVP1Fragment()
        val vp2fragment = LoginVP2Fragment()
        val vp3fragment = LoginVP3Fragment()

        // 프래그먼트를 배열에 쏘옥!
        var vpfragments = ArrayList<Fragment>()
        vpfragments.add(vp1fragment)
        vpfragments.add(vp2fragment)
        vpfragments.add(vp3fragment)

        // 뷰페이저 연결, 도트인디케이터 연결
        var adapter = LoginPageAdapter(this, vpfragments)
        binding.loginVp2.adapter = adapter
        binding.loginDi.attachTo(binding.loginVp2)

        // 뷰페이저 자동 스크롤
        handler= Handler(Looper.getMainLooper()){
            setPage()
            true
        }
        Thread(PagerRunnable()).start()


        /* kakao 로그인 버튼 */
        binding.loginBtnKakaoLogin.setOnClickListener {
            //todo
        }

        /* 다른 방법으로 로그인 버튼 */
        binding.loginBtnAnotherLogin.setOnClickListener {
            val bottomSheetLogin = BottomSheet()
            bottomSheetLogin.show(supportFragmentManager, bottomSheetLogin.tag)
        }

    }

    inner class PagerRunnable:Runnable{
        override fun run() {
            while(true){
                try {
                    Thread.sleep(2000)
                    handler.sendEmptyMessage(0)
                } catch (e : InterruptedException){
                    Log.d("interupt", "interupt발생")
                }
            }
        }
    }

    //페이지 변경하기
    fun setPage() {
        if(currentPosition==4) {
            currentPosition=0
        }
        binding.loginVp2.setCurrentItem(currentPosition,true)
        currentPosition++
    }
}
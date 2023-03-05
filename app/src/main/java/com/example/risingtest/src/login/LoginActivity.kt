package com.example.risingtest.src.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.ActivityLoginBinding
import com.example.risingtest.src.login.bottomSheet.BottomSheet
import com.example.risingtest.src.login.viewPager2.LoginVP1Fragment
import com.example.risingtest.src.login.viewPager2.LoginVP2Fragment
import com.example.risingtest.src.login.viewPager2.LoginVP3Fragment
import com.example.risingtest.src.login.viewPager2.PageAdapter

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

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
        var adapter = PageAdapter(this, vpfragments)
        binding.loginVp2.adapter = adapter
        binding.loginDi.attachTo(binding.loginVp2)

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
}
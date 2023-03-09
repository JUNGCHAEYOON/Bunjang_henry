package com.example.risingtest.src.login.LoginGetToken

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityLoginGetTokenBinding
import com.example.risingtest.src.login.LoginActivity
import com.example.risingtest.src.login.LoginGetToken.models.LGTResponse
import com.example.risingtest.src.login.LoginGetToken.models.LGTRequest
import com.example.risingtest.src.main.MainActivity

class LoginGetTokenActivity :
    BaseActivity<ActivityLoginGetTokenBinding>(ActivityLoginGetTokenBinding::inflate),LoginGetTokenInterface {

    lateinit var userName: String
    var userPhone: String = ""
    lateinit var userId: String
    lateinit var userPw: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lgtBtnRegister()
    }


    // 회원가입 버튼!
    fun lgtBtnRegister() {
        binding.lgtBtnRegister.setOnClickListener {
            if (binding.lgtEtvName.text.toString() != "") {
                binding.lgtLlPhonenumber.visibility = View.VISIBLE
                binding.lgtTvWhat.text = "전화번호를"
            }

            if (binding.lgtEtvPhonenumber.text.toString() != "") {
                binding.lgtLlIdpw.visibility = View.VISIBLE
                binding.lgtTvWhat.text = "아이디, 패스워드를"
            }

            if (binding.lgtEtvId.text.toString() != "" && binding.lgtEtvPw.text.toString() != "") {

                var buff = binding.lgtEtvPhonenumber.text.toString()
                for (i in buff){
                    userPhone += i
                    if(userPhone.length == 3 || userPhone.length == 8){
                        userPhone += "-"
                    }
                }
                userName = binding.lgtEtvName.text.toString()
                userId = binding.lgtEtvId.text.toString()
                userPw = binding.lgtEtvPw.text.toString()

                val lgtRequest = LGTRequest(name = userName, phoneNumber = userPhone, uid = userId, password = userPw)
                LoginGetTokenService(this).tryPostLoginGetToken(lgtRequest)
            }
        }
    }

    override fun onPostLoginGetTokenSuccess(response: LGTResponse) {
        //sharedpreferences 에 jwt 토큰 저장!
        ApplicationClass.sSharedPreferences.edit().putString(ApplicationClass.X_ACCESS_TOKEN,response.result?.jwt.toString()).apply()
    
        if(response.result?.jwt != null){
            showCustomToast("회원가입이 완료되었습니다.")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            LoginActivity().finish()
        }else{
            userPhone = ""
            showCustomToast(response.message.toString())
        }
    }

    override fun onPostLoginGetTokenFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}
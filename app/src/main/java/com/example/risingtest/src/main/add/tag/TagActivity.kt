package com.example.risingtest.src.main.add.tag

import android.os.Bundle
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityTagBinding

class TagActivity : BaseActivity<ActivityTagBinding>(ActivityTagBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        // 뒤로가기
        binding.tagBtnBack.setOnClickListener {
            finish()
        }

        // 체크버튼
        binding.tagBtnCheck.setOnClickListener {
            finish()
        }
    }
}
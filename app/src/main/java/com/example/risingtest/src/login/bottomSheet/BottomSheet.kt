package com.example.risingtest.src.login.bottomSheet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.risingtest.R
import com.example.risingtest.src.main.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheet() : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.dialog_bottom_sheet_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //페이스북으로 시작하기 버튼
        view.findViewById<LinearLayout>(R.id.login_btn_bottom_facebook).setOnClickListener {

        }

        // 네이버로 시작하기 버튼
        view.findViewById<LinearLayout>(R.id.login_btn_bottom_naver).setOnClickListener {

        }

        //본인인증으로 시작하기 버튼
        view.findViewById<LinearLayout>(R.id.login_btn_bottom_bonin).setOnClickListener {
            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)
        }

    }
}
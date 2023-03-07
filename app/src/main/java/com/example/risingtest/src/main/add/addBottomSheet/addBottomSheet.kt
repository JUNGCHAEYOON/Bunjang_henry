package com.example.risingtest.src.main.add.addBottomSheet

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.risingtest.R
import com.example.risingtest.src.main.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationBarMenu

class addBottomSheet() : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.dialog_bottom_sheet_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 상품상태 버튼
        val sangtaeOp1 = view.findViewById<TextView>(R.id.add_btn_junggo)
        val sangtaeOp2 = view.findViewById<TextView>(R.id.add_btn_saesangpum)
        sangtaeOp1.setOnClickListener {
            sangtaeOp1.setTextColor(Color.parseColor("#ff0000"))
            sangtaeOp1.setBackgroundResource(R.drawable.radius_whitepink)
            sangtaeOp2.setTextColor(Color.parseColor("#AAAAAA"))
            sangtaeOp2.setBackgroundResource(R.drawable.radius_mypage_point)
        }
        sangtaeOp2.setOnClickListener {
            sangtaeOp2.setTextColor(Color.parseColor("#ff0000"))
            sangtaeOp2.setBackgroundResource(R.drawable.radius_whitepink)
            sangtaeOp1.setTextColor(Color.parseColor("#AAAAAA"))
            sangtaeOp1.setBackgroundResource(R.drawable.radius_mypage_point)
        }

        // 교환 버튼
        val gyohwanOp1 = view.findViewById<TextView>(R.id.add_btn_bulga)
        val gyohwanOp2 = view.findViewById<TextView>(R.id.add_btn_ganung)
        gyohwanOp1.setOnClickListener {
            gyohwanOp1.setTextColor(Color.parseColor("#ff0000"))
            gyohwanOp1.setBackgroundResource(R.drawable.radius_whitepink)
            gyohwanOp2.setTextColor(Color.parseColor("#AAAAAA"))
            gyohwanOp2.setBackgroundResource(R.drawable.radius_mypage_point)
        }
        gyohwanOp2.setOnClickListener {
            gyohwanOp2.setTextColor(Color.parseColor("#ff0000"))
            gyohwanOp2.setBackgroundResource(R.drawable.radius_whitepink)
            gyohwanOp1.setTextColor(Color.parseColor("#AAAAAA"))
            gyohwanOp1.setBackgroundResource(R.drawable.radius_mypage_point)
        }
        // 지역 버튼
        view.findViewById<LinearLayout>(R.id.add_btn_location).setOnClickListener {
            //todo
        }

        // 선택 완료 버튼
        view.findViewById<TextView>(R.id.add_btn_finish).setOnClickListener {
            dismiss()
        }

    }
}
package com.example.risingtest.src.main.add.addBottomSheet

import android.app.Dialog
import android.content.Context
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
import com.example.risingtest.src.main.add.AddActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationBarMenu

class addBottomSheet() : BottomSheetDialogFragment() {

    // 인터페이스 생성
    interface addBottomSheetListener {
        fun onDataPassOp(op1 : Int, op2 : Boolean, op3 : Boolean,op4 : String)
    }
    lateinit var dataPassListener : addBottomSheetListener

    // 변수선언
    var option_howmany = 1
    var option_status = true        // true 중고, false 새상품
    var option_getBack = true       // true 불가, false 가능
    var option_location = "마포"     //디폴트 마포! 추후 선택지 설정예정

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPassListener = context as addBottomSheetListener //형변환
    }

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
            option_status = true
            sangtaeOp1.setTextColor(Color.parseColor("#ff0000"))
            sangtaeOp1.setBackgroundResource(R.drawable.radius_whitepink)
            sangtaeOp2.setTextColor(Color.parseColor("#AAAAAA"))
            sangtaeOp2.setBackgroundResource(R.drawable.radius_mypage_point)
        }
        sangtaeOp2.setOnClickListener {
            option_status = false
            sangtaeOp2.setTextColor(Color.parseColor("#ff0000"))
            sangtaeOp2.setBackgroundResource(R.drawable.radius_whitepink)
            sangtaeOp1.setTextColor(Color.parseColor("#AAAAAA"))
            sangtaeOp1.setBackgroundResource(R.drawable.radius_mypage_point)
        }

        // 교환 버튼
        val gyohwanOp1 = view.findViewById<TextView>(R.id.add_btn_bulga)
        val gyohwanOp2 = view.findViewById<TextView>(R.id.add_btn_ganung)
        gyohwanOp1.setOnClickListener {
            option_getBack = true
            gyohwanOp1.setTextColor(Color.parseColor("#ff0000"))
            gyohwanOp1.setBackgroundResource(R.drawable.radius_whitepink)
            gyohwanOp2.setTextColor(Color.parseColor("#AAAAAA"))
            gyohwanOp2.setBackgroundResource(R.drawable.radius_mypage_point)
        }
        gyohwanOp2.setOnClickListener {
            option_getBack = false
            gyohwanOp2.setTextColor(Color.parseColor("#ff0000"))
            gyohwanOp2.setBackgroundResource(R.drawable.radius_whitepink)
            gyohwanOp1.setTextColor(Color.parseColor("#AAAAAA"))
            gyohwanOp1.setBackgroundResource(R.drawable.radius_mypage_point)
        }
        // 지역 버튼
        view.findViewById<LinearLayout>(R.id.add_btn_location).setOnClickListener {
            //todo
        }

        /* 이 프래그먼트에서 dismiss 와 동시에 addAdctivity 로 변수를 넘겨줍니다. 해당 변수는
        * 액티비티로 넘어간뒤 정제되어 텍스트뷰에 들어가고 이 데이터들이 일렬화 되어 post 됩니다.
        * */
        // 선택 완료 버튼
        view.findViewById<TextView>(R.id.add_btn_finish).setOnClickListener {
            option_howmany = view.findViewById<TextView>(R.id.add_etv_howmany).text.toString().toInt()
            
            // 변수 4개 addActivity로 전달
            dataPassListener.onDataPassOp(option_howmany,option_status,option_getBack,option_location)
            
            //dialog 종료
            dismiss()
        }

    }
}
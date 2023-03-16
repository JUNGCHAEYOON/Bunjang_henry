package com.example.risingtest.src.main.home.itemdomain.pay

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.risingtest.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PayBottomSheet : BottomSheetDialogFragment() {
    
    interface payBottomSheetListener{
        // P = 1 : 문앞, 2 : 직접 받고 부재 시 문앞, 3 : 경비실, 4 : 우편함, 5 : 직접입력
        fun onDataPass(P : Int)
    }
    lateinit var  dataPassListener : payBottomSheetListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPassListener = context as payBottomSheetListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.dialog_bottom_sheet_pay,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val op1 = view.findViewById<TextView>(R.id.pay_btn_bs_1)
        val op2 = view.findViewById<TextView>(R.id.pay_btn_bs_2)
        val op3 = view.findViewById<TextView>(R.id.pay_btn_bs_3)
        val op4 = view.findViewById<TextView>(R.id.pay_btn_bs_4)
        op1.setOnClickListener {
            dataPassListener.onDataPass(1)
            dismiss()
        }
        op2.setOnClickListener {
            dataPassListener.onDataPass(2)
            dismiss()
        }
        op3.setOnClickListener {
            dataPassListener.onDataPass(3)
            dismiss()
        }
        op4.setOnClickListener {
            dataPassListener.onDataPass(4)
            dismiss()
        }
    }
}
package com.example.risingtest.src.main.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.risingtest.R
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.FragmentAddBinding
import com.example.risingtest.databinding.FragmentBungaetalkBinding

class AddFragment :
    BaseFragment<FragmentAddBinding>(FragmentAddBinding::bind, R.layout.fragment_add) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
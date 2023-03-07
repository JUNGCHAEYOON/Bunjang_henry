package com.example.risingtest.src.main.add

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingtest.R
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityAddBinding
import com.example.risingtest.src.main.add.addrv.addItem
import com.example.risingtest.src.main.add.addrv.addRecyclerAdapter

class AddActivity : BaseActivity<ActivityAddBinding>(ActivityAddBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*툴바 설정 및 뒤로가기 구현*/
        setSupportActionBar(binding.addTb)
        // 뒤로가기 버튼
        binding.addBtnBack.setOnClickListener {
            finish()
        }
        // 글쓰기 버튼
        binding.addBtnAdd.setOnClickListener {
            showCustomToast("등록이 완료 되었습니다!")
            finish()
        }

        /* 사진 불러오기 최대 12장 저장하기 */
        getPicRecycler()
    }

    /* 사진 불러오기 최대 12장 저장하기 */
    fun getPicRecycler(){
        /* 리싸이클러뷰 파트 */

        // 리싸이클러뷰 변수 선언
        val rv = binding.addRvPic

        // rv에 들어갈 아이템 선언
        val itemList = ArrayList<addItem>()

        /*
        *이부분이 카메라로부터 받아와서 사진 추가하는 부분!!
        * */
        // itemList 에 RecyclerItem 추가
        itemList.add(addItem(R.drawable.radius_red))
        itemList.add(addItem(R.drawable.radius_red))
        itemList.add(addItem(R.drawable.radius_red))
        itemList.add(addItem(R.drawable.radius_red))


        // 어댑터 변수 선언
        val recyclerAdapter = addRecyclerAdapter(itemList)

        // 리스트의 크기와 아이템이 둘다 변경되는 경우에 사용되는 메서드, 리싸이클러뷰 갱신
        recyclerAdapter.notifyDataSetChanged()

        // 어댑터 연결
        rv.adapter = recyclerAdapter
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}
package com.example.risingtest.src.main.add

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingtest.R
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityAddBinding
import com.example.risingtest.src.main.add.addBottomSheet.addBottomSheet
import com.example.risingtest.src.main.add.addrv.addItem
import com.example.risingtest.src.main.add.addrv.addRecyclerAdapter

class AddActivity : BaseActivity<ActivityAddBinding>(ActivityAddBinding::inflate), addBottomSheet.addBottomSheetListener {

    // post 로 넘겨줄 변수들
    private lateinit var pic : ArrayList<Int>       // 사진 리스트
    private lateinit var name : String              // 제품명
    private lateinit var category : String          // 카테고리
    private lateinit var tag : ArrayList<String>    // 태그 리스트
    private var price : Int = 0                     // 가격
    private var howmany : Int = 1                   // 수량

    private var oldornew : Boolean = true           // true 중고      : false 새상품
    private var getback : Boolean = true            // true 교환불가   : false 교환 가능
    private lateinit var location : String          // 교환지역
    private lateinit var info : String              // 설명

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
            
            // POST 실행 함수
            postAll()

            //액티비티 종료
            finish()
        }

        /* 사진 불러오기 최대 12장 저장하기 */
        binding.addBtnAddpic.setOnClickListener {
            getPicRecycler()
        }

        /* 옵션선택 버튼 클릭 */
        binding.addBtnOption.setOnClickListener {
            choiceOptionBtnClick()
        }
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

    /* 옵션선택 버튼 클릭 */
    fun choiceOptionBtnClick(){
        val addBottomSheet = addBottomSheet()
        addBottomSheet.show(supportFragmentManager,addBottomSheet.tag)
    }
    // 옵션선택 interface
    override fun onDataPassOp(op1: Int, op2: Boolean, op3: Boolean, op4: String) {
        //op1
        binding.addTvHowmany.text = op1.toString() + "개"
        
        //op2
        if(op2 == true) {
            oldornew = true
            binding.addTvJunggoOrNew.text = "중고상품"
        }else{
            oldornew = false
            binding.addTvJunggoOrNew.text = "새상품"
        }
        
        //op3
        if(op3 == true){
            getback = true
            binding.addTvGyohwan.text = "교환불가"
        }else{
            getback = false
            binding.addTvGyohwan.text = "교환가능"
        }
        
        //op4 디폴트 마포
        binding.addTvJiyeok.text = op4
    }

    /* 값들을 모두 post 하기 */
    fun postAll(){

    }
}
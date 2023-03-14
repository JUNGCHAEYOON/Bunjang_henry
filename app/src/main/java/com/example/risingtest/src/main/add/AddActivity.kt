package com.example.risingtest.src.main.add

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingtest.R
import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityAddBinding
import com.example.risingtest.src.main.add.addBottomSheet.addBottomSheet
import com.example.risingtest.src.main.add.addrv.addItem
import com.example.risingtest.src.main.add.addrv.addRecyclerAdapter
import com.example.risingtest.src.main.add.category.CategoryActivity
import com.example.risingtest.src.main.add.tag.TagActivity

class AddActivity : BaseActivity<ActivityAddBinding>(ActivityAddBinding::inflate), addBottomSheet.addBottomSheetListener {
    //카테고리 실행 완료여부
    var isCategorySelected = false

    // post 로 넘겨줄 변수들
    private lateinit var pic : ArrayList<Int>       // 사진 리스트
    private lateinit var name : String              // 제품명

    var categoryId1 : Int = 0                       // 카테고리 대 아이디
    var categoryId2 : Int = 0                       // 카테고리 중 아이디
    var categoryId3 : Int = 0                       // 카테고리 소 아이디
    lateinit var categoryTitle1 : String            // 카테고리 대 이름
    lateinit var categoryTitle2 : String            // 카테고리 중 이름
    lateinit var categoryTitle3 : String            // 카테고리 소 이름

    private lateinit var tag : ArrayList<String>    // 태그 리스트
    private var price : Int = 0                     // 가격
    private var howmany : Int = 1                   // 수량

    private var hasDeliveryFee = false

    private var oldornew : Boolean = true           // true 중고      : false 새상품
    private var getback : Boolean = true            // true 교환불가   : false 교환 가능
    private lateinit var location : String          // 교환지역
    private lateinit var info : String              // 설명

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationClass.sSharedPreferences.edit().putBoolean("isCategorySelected",false).apply()
    }

    override fun onResume() {
        super.onResume()

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

        /* 사진 불러오기 최대 5장 저장하기 */
        binding.addBtnAddpic.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            activityResult.launch(intent)
            showCustomToast("최대 5장까지만 가능합니다.")
        }

        /* 카테고리 선택 */
        binding.addBtnCategory.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            isCategorySelected = true
            startActivity(intent)
        }
        // 카테고리를 이미 설정한경우 해당 변수가 변경됨
        if(ApplicationClass.sSharedPreferences.getBoolean("isCategorySelected",false) == true){
            categoryTitle1 = ApplicationClass.sSharedPreferences.getString("category1","대").toString()
            categoryTitle2 = ApplicationClass.sSharedPreferences.getString("category2","중").toString()
            categoryTitle3 = ApplicationClass.sSharedPreferences.getString("category3","소").toString()
            categoryId1 = ApplicationClass.sSharedPreferences.getInt("category1Id",0)
            categoryId2 = ApplicationClass.sSharedPreferences.getInt("category2Id",0)
            categoryId3 = ApplicationClass.sSharedPreferences.getInt("category3Id",0)

            binding.addTv1.text = categoryTitle1
            binding.addTv2.text = categoryTitle2
            binding.addTv3.text = categoryTitle3
            binding.addLlCategory.visibility = View.VISIBLE
            binding.addTvCategory.visibility = View.GONE
        }

        /* 태그 선택 */
        binding.addTvTag.setOnClickListener {
            val intent = Intent(this, TagActivity::class.java)
            startActivity(intent)
        }

        /* 배송비 포함 체크박스 */
        binding.addCbHasdeliveryfee.setOnClickListener {
            if(hasDeliveryFee == false){
                binding.addCbHasdeliveryfee.setImageResource(R.drawable.ic_action_checkbox_red)
                hasDeliveryFee = true
            }else{
                binding.addCbHasdeliveryfee.setImageResource(R.drawable.ic_action_checkbox_grey)
                hasDeliveryFee = false
            }
        }

        /* 옵션선택 버튼 클릭 */
        binding.addBtnOption.setOnClickListener {
            choiceOptionBtnClick()
        }
    }

    override fun onDestroy() {
        ApplicationClass.sSharedPreferences.edit().putBoolean("isCategorySelected",false).apply()
        super.onDestroy()
    }

    /* 사진 불러오기 최대 5장 저장하기 */
    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        // 리싸이클러뷰 변수 선언
        val rv = binding.addRvPic
        // rv에 들어갈 아이템 선언
        val itemList = ArrayList<addItem>()

        //결과 코드 OK , 결가값 null 아니면
        if(it.resultCode == RESULT_OK){

            //멀티 선택은 clipData
            if(it.data!!.clipData != null){ //멀티 이미지

                //선택한 이미지 갯수
                val count = it.data!!.clipData!!.itemCount

                //5보다 작아야지만 할당함
                if(count <= 5){
                    binding.addTvHowmanypic.text = count.toString() + "/5"
                    for(index in 0 until count){
                        //이미지 담기
                        val imageUri = it.data!!.clipData!!.getItemAt(index).uri
                        //이미지 추가
                        itemList.add(addItem(imageUri))
                    }
                //5 보다 크면 5만 할당
                }else{
                    binding.addTvHowmanypic.text = "5/5"
                    for(index in 0 until 5){
                        //이미지 담기
                        val imageUri = it.data!!.clipData!!.getItemAt(index).uri
                        //이미지 추가
                        itemList.add(addItem(imageUri))
                    }
                }

            }else{ //싱글 이미지
                val imageUri = it.data!!.data
                itemList.add(addItem(imageUri!!))
                binding.addTvHowmanypic.text = "1/5"
            }
        }
        Log.d("BBBBBBBBBBBBBBBBBBBBBBB",itemList.toString())

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
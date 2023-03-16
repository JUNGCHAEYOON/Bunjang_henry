package com.example.risingtest.src.main.add

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toFile
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingtest.R
import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityAddBinding
import com.example.risingtest.src.main.add.addBottomSheet.addBottomSheet
import com.example.risingtest.src.main.add.addmodels.AddRequest
import com.example.risingtest.src.main.add.addmodels.AddResponse
import com.example.risingtest.src.main.add.addrv.addItem
import com.example.risingtest.src.main.add.addrv.addRecyclerAdapter
import com.example.risingtest.src.main.add.category.CategoryActivity
import com.example.risingtest.src.main.add.tag.TagActivity
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


class AddActivity : BaseActivity<ActivityAddBinding>(ActivityAddBinding::inflate),
    addBottomSheet.addBottomSheetListener, AddInterface {
    //카테고리 실행 완료여부
    var isCategorySelected = false


    // 이미지파일 5장
    var file = ArrayList<File>()
    var map = HashMap<String, RequestBody>()

    // post 로 넘겨줄 변수들
    var amount: Int? = 0
    var checkExchange: String? = "Y"
    var checkNewProduct: String? = "Y"
    var checkPay: String? = "Y"
    var content: String? = ""
    var hasDeliveryFee: String? = "Y"
    var majorCategoryId: Int? = -1
    var middleCategoryId: Int? = -1
    var price: Int? = 0
    var region: String? = ""
    var subCategoryId: Int? = -1
    private var tagIds = ArrayList<Int?>()          // 태그 리스트
    var title: String? = ""
    var userId: Int? = 0


    var categoryId1: Int = 0                       // 카테고리 대 아이디
    var categoryId2: Int = 0                       // 카테고리 중 아이디
    var categoryId3: Int = 0                       // 카테고리 소 아이디
    lateinit var categoryTitle1: String            // 카테고리 대 이름
    lateinit var categoryTitle2: String            // 카테고리 중 이름
    lateinit var categoryTitle3: String            // 카테고리 소 이름


    private var hasDeliveryFeeBool = false

    private var oldornew: Boolean = true           // true 중고      : false 새상품
    private var getback: Boolean = true            // true 교환불가   : false 교환 가능
    private lateinit var location: String          // 교환지역
    private lateinit var info: String              // 설명

    private var isSafePayed: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //액티비티 시작시 초기화
        ApplicationClass.sSharedPreferences.edit().putBoolean("isCategorySelected", false).apply()
        ApplicationClass.sSharedPreferences.edit().putBoolean("isTagSelected", false).apply()
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
            // POST 실행 변수

            /* 변수 */
            //글쓰기
            content = binding.addEtvExplanation.text.toString()
            //가격
            price = binding.addEtvPrice.text.toString().toInt()
            //타이틀
            title = binding.addEtvProductname.text.toString()
            //유저아이디
            userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

            val postProductReq = AddRequest(
                amount = amount,
                checkExchange = checkExchange,
                checkNewProduct = checkNewProduct,
                checkPay = checkPay,
                content = content,
                hasDeliveryFee = hasDeliveryFee,
                majorCategoryId = majorCategoryId,
                middleCategoryId = middleCategoryId,
                price = price,
                region = region,
                subCategoryId = subCategoryId,
                tagIds = tagIds,
                title = title,
                userId = userId
            )

//            var _amount = amount.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//            var _checkExchange = checkExchange.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//            var _checkNewProduct = checkNewProduct.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//            var _checkPay = checkPay.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//            var _content = content.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//
//            var _hasDeliveryFee = hasDeliveryFee.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//            var _majorCategoryId = majorCategoryId.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//            var _middleCategoryId = middleCategoryId.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//            var _price = price.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//            var _region = region.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//
//            var _subCategoryId = subCategoryId.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//            var _tagIds = tagIds.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//            var _title = title.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//            var _userId = userId.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//
//            map["amount"] = _amount
//            map["checkExchange"] = _checkExchange
//            map["checkNewProduct"] = _checkNewProduct
//            map["checkPay"] = _checkPay
//            map["content"] = _content
//
//            map["hasDeliveryFee"] = _hasDeliveryFee
//            map["majorCategoryId"] = _majorCategoryId
//            map["middleCategoryId"] = _middleCategoryId
//            map["price"] = _price
//            map["region"] = _region
//
//            map["subCategoryId"] = _subCategoryId
//            map["tagIds"] = _tagIds
//            map["title"] = _title
//            map["userId"] = _userId

            // Post 시작

            // file : ArrayList<File> -> ArrayList<MultipartBody.Part>
            // postProductReq : AddResponse -> MultipartBody.Part
            val P1 = ArrayList<MultipartBody.Part>()
            for (i in file) {
//                val requestBody = i.asRequestBody("image/*".toMediaTypeOrNull())
//                val m = MultipartBody.Part.createFormData("images", i.name, requestBody)
//                P1.add(m)

                val requestFile = i.asRequestBody("image/jpg".toMediaTypeOrNull())
                val body = MultipartBody.Part.createFormData("file", i.name, requestFile)
                P1.add(body)
            }

            Log.d("LLLLLLLLLL", P1.toString())
            Log.d("LLLLLLLLLL", postProductReq.toString())
            AddService(this).tryPostAdd(P1, postProductReq)
//
////            액티비티 종료
            showCustomToast("등록이 완료 되었습니다!")
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
        if (ApplicationClass.sSharedPreferences.getBoolean("isCategorySelected", false) == true) {
            categoryTitle1 =
                ApplicationClass.sSharedPreferences.getString("category1", "대").toString()
            categoryTitle2 =
                ApplicationClass.sSharedPreferences.getString("category2", "중").toString()
            categoryTitle3 =
                ApplicationClass.sSharedPreferences.getString("category3", "소").toString()
            categoryId1 = ApplicationClass.sSharedPreferences.getInt("category1Id", 0)
            majorCategoryId = categoryId1
            categoryId2 = ApplicationClass.sSharedPreferences.getInt("category2Id", 0)
            middleCategoryId = categoryId2
            categoryId3 = ApplicationClass.sSharedPreferences.getInt("category3Id", 0)
            subCategoryId = categoryId3

            binding.addTv1.text = categoryTitle1
            binding.addTv2.text = categoryTitle2
            binding.addTv3.text = categoryTitle3
            binding.addLlCategory.visibility = View.VISIBLE
            binding.addTvCategory.visibility = View.GONE
        }

        /* 태그 선택 */
        binding.addBtnTag.setOnClickListener {
            ApplicationClass.sSharedPreferences.edit().putBoolean("isTagSelected", false).apply()
            val intent = Intent(this, TagActivity::class.java)
            startActivity(intent)
        }
        if (ApplicationClass.sSharedPreferences.getBoolean("isTagSelected", false) == true) {
            tagIds.clear()
            binding.addLlTag.visibility = View.VISIBLE
            binding.addTvTag1.text =
                "#" + ApplicationClass.sSharedPreferences.getString("tag1", "태그1번").toString()
            tagIds.add(ApplicationClass.sSharedPreferences.getInt("tag1Id", 0))
            binding.addTvTag2.visibility = View.GONE
            binding.addTvTag3.visibility = View.GONE
            // 태그2번 이 있으면 2번도 추가
            if (ApplicationClass.sSharedPreferences.getString("tag2", "태그2번")
                    .toString() != "태그2번"
            ) {
                binding.addTvTag2.text =
                    "#" + ApplicationClass.sSharedPreferences.getString("tag2", "태그2번").toString()
                binding.addTvTag2.visibility = View.VISIBLE
                tagIds.add(ApplicationClass.sSharedPreferences.getInt("tag2Id", 0))
                binding.addTvTag3.visibility = View.GONE
            }
            // 3번이 있으면 3번도
            if (ApplicationClass.sSharedPreferences.getString("tag3", "태그3번")
                    .toString() != "태그3번"
            ) {
                binding.addTvTag3.text =
                    "#" + ApplicationClass.sSharedPreferences.getString("tag3", "태그3번").toString()
                binding.addTvTag3.visibility = View.VISIBLE
                tagIds.add(ApplicationClass.sSharedPreferences.getInt("tag3Id", 0))
            }

            binding.addTvTagHint.visibility = View.GONE
        } else {
            tagIds.clear()
            binding.addLlTag.visibility = View.GONE
            binding.addTvTagHint.visibility = View.VISIBLE
        }

        /* 배송비 포함 체크박스 */
        binding.addCbHasdeliveryfee.setOnClickListener {
            if (hasDeliveryFeeBool == false) {
                binding.addCbHasdeliveryfee.setImageResource(R.drawable.ic_action_checkbox_red)
                hasDeliveryFeeBool = true
                hasDeliveryFee = "Y"
            } else {
                binding.addCbHasdeliveryfee.setImageResource(R.drawable.ic_action_checkbox_grey)
                hasDeliveryFeeBool = false
                hasDeliveryFee = "N"
            }
        }

        /* 옵션선택 버튼 클릭 */
        binding.addBtnOption.setOnClickListener {
            choiceOptionBtnClick()
        }

        /*안전결제 환영 클릭*/
        binding.addBtnSafepay.setOnClickListener {
            if (isSafePayed == true) {
                isSafePayed = false
                checkPay = "N"
                binding.addBtnSafepay.setBackgroundResource(R.drawable.radius_mypage_point)
                binding.addTvWelcomesafepay.setTextColor(Color.parseColor("#888888"))
                binding.addIvWelcomsafepay.setImageResource(R.drawable.ic_action_check_grey)
            } else {
                isSafePayed = true
                checkPay = "Y"
                binding.addBtnSafepay.setBackgroundResource(R.drawable.radius_stroke_deepred)
                binding.addTvWelcomesafepay.setTextColor(Color.parseColor("#000000"))
                binding.addIvWelcomsafepay.setImageResource(R.drawable.ic_action_check_red)
            }

        }
    }

    override fun onDestroy() {
        ApplicationClass.sSharedPreferences.edit().putBoolean("isCategorySelected", false).apply()
        super.onDestroy()
    }

    /* 사진 불러오기 최대 5장 저장하기 */
    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        // 리싸이클러뷰 변수 선언
        val rv = binding.addRvPic
        // rv에 들어갈 아이템 선언
        val itemList = ArrayList<addItem>()

        //결과 코드 OK , 결가값 null 아니면
        if (it.resultCode == RESULT_OK) {

            //멀티 선택은 clipDaa
            if (it.data!!.clipData != null) { //멀티 이미지

                //선택한 이미지 갯수
                val count = it.data!!.clipData!!.itemCount

                //5보다 작아야지만 할당함
                if (count <= 5) {
                    binding.addTvHowmanypic.text = count.toString() + "/5"
                    for (index in 0 until count) {
                        //이미지 담기
                        val imageUri = it.data!!.clipData!!.getItemAt(index).uri
                        //이미지 추가
                        itemList.add(addItem(imageUri))

                        // 파일로 변환
//                        val p = File(getRealPathFromURI(imageUri))
                        val p = File(absolutelyPath(imageUri, this))
                        // 파일 배열 추가
                        file.add(p)
                    }
                    //5 보다 크면 5만 할당
                } else {
                    binding.addTvHowmanypic.text = "5/5"
                    for (index in 0 until 5) {
                        //이미지 담기
                        val imageUri = it.data!!.clipData!!.getItemAt(index).uri
                        //이미지 추가
                        itemList.add(addItem(imageUri))
                        file.add(imageUri.toFile())
                    }
                }

            } else { //싱글 이미지
                val imageUri = it.data!!.data
                itemList.add(addItem(imageUri!!))
                binding.addTvHowmanypic.text = "1/5"
            }
        }
        Log.d("BBBBBBBBBBBBBBBBBBBBBBB", itemList.toString())

        // 어댑터 변수 선언
        val recyclerAdapter = addRecyclerAdapter(itemList)

        // 리스트의 크기와 아이템이 둘다 변경되는 경우에 사용되는 메서드, 리싸이클러뷰 갱신
        recyclerAdapter.notifyDataSetChanged()

        // 어댑터 연결
        rv.adapter = recyclerAdapter
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    fun absolutelyPath(path: Uri?, context: Context): String {
        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        var index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        var result = c?.getString(index!!)

        return result!!
    }

    private fun getRealPathFromURI(contentURI: Uri): String? {
        val result: String
        val cursor: Cursor? = contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath().toString()
        } else {
            cursor.moveToFirst()
            val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }


    /* 옵션선택 버튼 클릭 */
    fun choiceOptionBtnClick() {
        val addBottomSheet = addBottomSheet()
        addBottomSheet.show(supportFragmentManager, addBottomSheet.tag)
    }

    // 옵션선택 interface
    override fun onDataPassOp(op1: Int, op2: Boolean, op3: Boolean, op4: String) {
        //op1
        binding.addTvHowmany.text = op1.toString() + "개"
        amount = op1

        //op2
        if (op2 == true) {
            oldornew = true
            binding.addTvJunggoOrNew.text = "중고상품"
            checkNewProduct = "N"
        } else {
            oldornew = false
            binding.addTvJunggoOrNew.text = "새상품"
            checkNewProduct = "Y"
        }

        //op3
        if (op3 == true) {
            getback = true
            binding.addTvGyohwan.text = "교환불가"
            checkExchange = "N"
        } else {
            getback = false
            binding.addTvGyohwan.text = "교환가능"
            checkExchange = "Y"
        }

        //op4 디폴트 도내동
        binding.addTvJiyeok.text = op4
        region = op4
    }

    /* 값들을 모두 post 하기 */
    override fun onPostAddSuccess(response: AddResponse) {
        showCustomToast("등록성공")
    }

    override fun onPostAddFailure(message: String) {
        showCustomToast("등록실패")
    }
}
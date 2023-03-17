package com.example.risingtest.src.main.home.itemdomain.pay

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.risingtest.R
import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityPayBinding
import com.example.risingtest.src.main.home.itemdomain.pay.models.PayRequest
import com.example.risingtest.src.main.home.itemdomain.pay.models.PayResponse

class PayActivity : BaseActivity<ActivityPayBinding>(ActivityPayBinding::inflate), PayBottomSheet.payBottomSheetListener, PayInterface{

    var userId1 : Int = 0
    var deliveryAddressId : Int = 1
    var deliveryRequest : String = ""
    var commisionPrice : Int = 0
    var usedPoint : Int = 0
    var finalPrice : Int = 0
    var paymentMethod : String = "번개장터 간편결제"
    var email : String = ""
    var agreement : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        //불러온 데이타
        userId1 = ApplicationClass.sSharedPreferences.getInt("userId",0)
        val productId = intent.getIntExtra("productId",0)
        val imageuri = intent.getStringExtra("imageuri")
        val price = intent.getStringExtra("price")
        val title = intent.getStringExtra("title")
        finalPrice = intent.getIntExtra("realprice",0)

        //상품사진
        Glide.with(this).load(imageuri).into(binding.payIvImage)
        // 금액, 상품금액, 통결제 금액
        binding.payTvPrice.text = price
        binding.payTvPriceSangpum.text = price
        binding.payTvPriceTotal.text = price
        // 제목
        binding.payTvTitle.text = title

        // 배송지

        // 배송요청사항
        binding.payBtnBaesongyochung.setOnClickListener {
            val payBottomSheet = PayBottomSheet()
            payBottomSheet.show(supportFragmentManager, payBottomSheet.tag)
        }


        // 약관
        binding.payIvYakgwan.setOnClickListener {
            if(agreement == true){
                binding.payIvYakgwan.setImageResource(R.drawable.ic_action_checkbox_grey)
                agreement = false
            }else{
                binding.payIvYakgwan.setImageResource(R.drawable.ic_action_checkbox_red)
                agreement = true
            }
        }


        //결제하기 버튼 클릭
        binding.payBtnPayEnd.setOnClickListener {
            val P = PayRequest(
                agreement = agreement,
                commisionPrice = commisionPrice,
                deliveryAddressId = deliveryAddressId,
                deliveryRequest = deliveryRequest,
                email = email,
                finalPrice = finalPrice,
                paymentMethod = paymentMethod,
                usedPoint = usedPoint,
                userId = userId1
            )
            Log.d("QQQQQQQQQQQQQQQQQQQQQQQQ", userId1.toString())
            Log.d("QQQQQQQQQQQQQQQQQQQQQQQQ", ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN,null).toString())
            Log.d("QQQQQQQQQQQQQQQQQQQQQQQQ", P.toString())
            Log.d("QQQQQQQQQQQQQQQQQQQQQQQQ", productId.toString())
            PayService(this).tryPostPay(P,productId.toString())
//            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onDataPass(P: Int) {
        when(P){
            1->{
                deliveryRequest = "문앞에 놓아주세요"
            }
            2->{
                deliveryRequest = "부재시 문앞에 놓아주세요"
            }
            3->{
                deliveryRequest = "경비실에 맡겨주세요"
            }
            4->{
                deliveryRequest = "우편함에 넣어주세요"
            }
        }

        binding.payTvBaesongyochung.text = deliveryRequest
        binding.payTvBaesongyochung.setTextColor(Color.BLACK)
    }

    override fun onPostPaySuccess(response: PayResponse) {
        showCustomToast("결제완료")
        Log.d("QQQQQQQQQQQQQQQQQQQQQQQQ",response.message.toString())
        finish()
    }

    override fun onPostPayFailure(message: String) {
        showCustomToast("message")
        Log.d("QQQQQQQQQQQQQQQQQQQQQQQQ",message)
        finish()
    }
}
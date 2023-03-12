package com.example.risingtest.src.main.home.itemdomain

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityItemDomainBinding
import com.example.risingtest.src.main.home.itemdomain.models.ItemDomainResponse
import java.text.DecimalFormat

class ItemDomainActivity : BaseActivity<ActivityItemDomainBinding>(ActivityItemDomainBinding::inflate),ItemDomainActivityInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.itemdomainBtnBack.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        // id 받아오기
        val id = intent.getIntExtra("id",0)

        // api 호출하여 상품정보 조회
        ItemDomainService(this).tryGetProducts(id.toString())
    }

    /* 상품 정보 호출 */
    override fun onGetProductsSuccess(response: ItemDomainResponse) {
        Log.d("IIIIIIIIIIIIIIIIIIII", response.toString())
        val result = response.result

        //이미지
        setVp(result?.productImgUrl)
        //가격
        val dec = DecimalFormat("#,###")
        val buff = dec.format(result?.price)
        binding.itemdomainTvPrice.text = buff.toString() + "원"
        //번개페이 가능여부
        if(result?.isSafePay == "Y"){
            binding.itemdomainIvBungaepay.visibility = View.VISIBLE
        }else{
            binding.itemdomainIvBungaepay.visibility = View.INVISIBLE
        }
        //제목
        binding.itemdomainTvTitle.text = result?.title
        //위치
        binding.itemdomainTvLocation.text = result?.locationAddress
        //시간
        binding.itemdomainTvTime.text = result?.createdAt
        //조회수
        binding.itemdomainTvHowmanyviews.text = result?.view.toString()
        //좋아요수
        binding.itemdomainTvHowmanyheart.text = result?.likes.toString()
        //채팅수
        binding.itemdomainTvHowmanychat.text = result?.chatCounts.toString()
        //배송비별도 or 배송비포함
        if(result?.hasDeliveryFee == "Y"){
            binding.itemdomainTvHasdeliveryfee.text = "배송비별도"
        }else{
            binding.itemdomainTvHasdeliveryfee.text = "배송비포함"
        }
        //새상품 or 중고상품
        if(result?.isNew == "Y"){
            binding.itemdomainTvIsnew.text = "새상품"
        }else{
            binding.itemdomainTvIsnew.text = "중고상품"
        }
        //총몇개
        binding.itemdomainTvAmount.text = "총 " + result?.amount.toString() + "개"
        //교환가능 or 교환불가
        if(result?.isSafePay == "Y"){
            binding.itemdomainIsInterchangeable.text = "교환가능"
        }else{
            binding.itemdomainIsInterchangeable.text = "교환불가"
        }
        //설명
        binding.itemdomainTvContent.text = result?.content
        //카테고리이미지
        Glide.with(this).load(result?.categoryImgUrl).into(binding.itemdomainIvCategoryImgUrl)
        //카테고리
        binding.itemdomainTvCategory.text = result?.categoryTitle
    }
    override fun onGetProductsFailure(message: String) {
        showCustomToast(message)
    }

    // 뷰페이저 이미지 붙이기
    fun setVp(list: List<String?>?){
        if (list != null) {
            val arr = ArrayList<String>()
            for(i in list){
                arr.add(i.toString())
            }
            binding.itemdomainVp.adapter = ItemDomainAdapter(this, arr)
            val child = binding.itemdomainVp.getChildAt(0)
            (child as? RecyclerView)?.overScrollMode = View.OVER_SCROLL_NEVER
        }
    }
}
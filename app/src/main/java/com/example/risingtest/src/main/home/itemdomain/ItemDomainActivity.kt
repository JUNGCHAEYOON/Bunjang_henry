package com.example.risingtest.src.main.home.itemdomain

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingtest.R
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityItemDomainBinding
import com.example.risingtest.src.main.home.itemdomain.models.ItemDomainResponse
import com.example.risingtest.src.main.home.itemdomain.storemodels.StoreInfoResponse
import java.text.DecimalFormat

class ItemDomainActivity : BaseActivity<ActivityItemDomainBinding>(ActivityItemDomainBinding::inflate),ItemDomainActivityInterface {
    
    // 좋아요표시 변수
    var heart : Boolean = false

    // 현재 팔로우 상태 : true 팔로잉 / false 팔로우
    var isFollowed = true

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
        val userId = intent.getIntExtra("userId", 0)

        // 뒤로가기 버튼

        // api 호출하여 상품정보 조회
        ItemDomainService(this).tryGetProducts(id.toString())

        // 상점정보 불러오기
        ItemDomainService(this).tryGetStoreInfo(userId.toString())

        // 상점이 판매중인 상품 불러오기

        //
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
        if(result?.dayCreatedFrom == 0){
            if(result?.hourCreatedFrom == 0){
                binding.itemdomainTvTime.text = "방금전"
            }else{
                binding.itemdomainTvTime.text = result?.hourCreatedFrom.toString() + "시간전"
            }
        }else if(result?.dayCreatedFrom!! <= 3){
            binding.itemdomainTvTime.text = result?.dayCreatedFrom.toString() + "일전"
        }else{
            binding.itemdomainTvTime.text = result?.createdAt
        }

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
        //하트
        binding.itemdomainIvHeart.setOnClickListener {
            if(heart == false){
                binding.itemdomainIvHeart.setImageResource(R.drawable.ic_action_redheart)
                heart = true
            }else{
                binding.itemdomainIvHeart.setImageResource(R.drawable.ic_action_greyheart)
                heart = false
            }
        }
        //브랜드이미지
        Glide.with(this).load(result?.brandImgUrl).into(binding.itemdomainIvBrandimage)

        //브랜드명
        binding.itemdomainTvBrandname.text = result?.brandName

        //번개톡 버튼

        //안전하게 결제하기

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

    /* 프로필 불러오기 */
    override fun onGetStoreInfoSuccess(response: StoreInfoResponse) {
        val result = response.result
        // 프로필 이미지
        Glide.with(this).load(result?.profileUrl).into(binding.itemdomainIvProfile)
        //상점닉네임
        binding.itemdomainTvStorename.text = result?.userName
        //별점
        binding.itemdomainTvStorepoint.text = result?.starRating.toString()
        //팔로워수
        binding.itemdomainTvStorefollower.text = result?.follower.toString()
        //팔로우 여부
        if(result?.isFollow == "Y"){
            val _v = binding.itemdomainBtnFollow
            _v.text = "팔로잉"
            _v.setTextColor(Color.BLACK)
            _v.setBackgroundResource(R.drawable.radius_mypage_point)
            isFollowed = true
        }else{
            val _v = binding.itemdomainBtnFollow
            _v.text = "팔로우"
            _v.setTextColor(Color.RED)
            _v.setBackgroundResource(R.drawable.radius_whitepink)
            isFollowed = false
        }

        //팔로우 버튼 클릭
        binding.itemdomainBtnFollow.setOnClickListener {
            if(isFollowed){
                val _v = binding.itemdomainBtnFollow
                _v.text = "팔로우"
                _v.setTextColor(Color.RED)
                _v.setBackgroundResource(R.drawable.radius_whitepink)
                isFollowed = false
            }else{
                val _v = binding.itemdomainBtnFollow
                _v.text = "팔로잉"
                _v.setTextColor(Color.BLACK)
                _v.setBackgroundResource(R.drawable.radius_mypage_point)
                isFollowed = true
            }
        }
    }

    override fun onGetStoreInfoFailure(message: String) {
        TODO("Not yet implemented")
    }

    /* 이상점의 상품 */

}
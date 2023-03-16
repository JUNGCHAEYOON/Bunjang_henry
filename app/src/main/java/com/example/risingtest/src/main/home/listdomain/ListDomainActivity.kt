package com.example.risingtest.src.main.home.listdomain

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityListDomainBinding
import com.example.risingtest.databinding.ActivityMainBinding
import com.example.risingtest.src.main.home.HomeFragmentInterface
import com.example.risingtest.src.main.home.HomeService
import com.example.risingtest.src.main.home.bannermodels.HomeAdViewResponse
import com.example.risingtest.src.main.home.listRecycler.RecyclerAdapter
import com.example.risingtest.src.main.home.listRecycler.RecyclerItem
import com.example.risingtest.src.main.home.listmodels.HomeListResponse
import com.example.risingtest.src.main.home.listmodels.ManPadding

class ListDomainActivity : BaseActivity<ActivityListDomainBinding>(ActivityListDomainBinding::inflate), HomeFragmentInterface {
    var title : String = "제목없음"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뒤로가기
        binding.listdomainBtnBack.setOnClickListener {
            finish()
        }

        // 타이틀 받아오기
        title = intent.getStringExtra("title").toString()
        binding.listdomainTvTitle.text = title

        // 리싸이클러뷰 실행
        HomeService(this).tryGetList()
    }


    // 배너 구현x
    override fun onGetBannersSuccess(response: HomeAdViewResponse) {
        TODO("Not yet implemented")
    }
    override fun onGetBannersFailure(message: String) {
        TODO("Not yet implemented")
    }

    /* 리스트 구현 */
    override fun onGetListSuccess(response: HomeListResponse) {
        when(title){
            "남성의류 > 패딩/점퍼" -> {
                val arr = response.result?.manPadding!!
                val itemlist = ArrayList<LDItem>()
                for(i in arr){
                    itemlist.add(LDItem(
                        i?.categoryTitle,
                        i?.id,
                        i?.price,
                        i?.safeCare,
                        i?.safePay,
                        i?.title,
                        i?.url
                    ))
                    Log.d("CCCCCCCCCCCCCCCCCCCCCC", i?.id.toString())
                }
                val recyclerAdapter = LDAdapter(itemlist)
                recyclerAdapter.notifyDataSetChanged()
                binding.listdomainRv.adapter = recyclerAdapter
                binding.listdomainRv.layoutManager = GridLayoutManager(this,2)
            }
            "신발 > 남성화" ->{
                val arr = response.result?.manShoes!!
                val itemlist = ArrayList<LDItem>()
                for(i in arr){
                    itemlist.add(LDItem(
                        i?.categoryTitle,
                        i?.id,
                        i?.price,
                        i?.safeCare,
                        i?.safePay,
                        i?.title,
                        i?.url
                    ))
                }
                val recyclerAdapter = LDAdapter(itemlist)
                recyclerAdapter.notifyDataSetChanged()
                binding.listdomainRv.adapter = recyclerAdapter
                binding.listdomainRv.layoutManager = GridLayoutManager(this,2)
            }
            "신발 > 스니커즈" ->{
                val arr = response.result?.sneakers!!
                val itemlist = ArrayList<LDItem>()
                for(i in arr){
                    itemlist.add(LDItem(
                        i?.categoryTitle,
                        i?.id,
                        i?.price,
                        i?.safeCare,
                        i?.safePay,
                        i?.title,
                        i?.url
                    ))
                }
                val recyclerAdapter = LDAdapter(itemlist)
                recyclerAdapter.notifyDataSetChanged()
                binding.listdomainRv.adapter = recyclerAdapter
                binding.listdomainRv.layoutManager = GridLayoutManager(this,2)

            }
            "여성의류 > 패딩/점퍼" ->{
                val arr = response.result?.womenPadding!!
                val itemlist = ArrayList<LDItem>()
                for(i in arr){
                    itemlist.add(LDItem(
                        i?.categoryTitle,
                        i?.id,
                        i?.price,
                        i?.safeCare,
                        i?.safePay,
                        i?.title,
                        i?.url
                    ))
                }
                val recyclerAdapter = LDAdapter(itemlist)
                recyclerAdapter.notifyDataSetChanged()
                binding.listdomainRv.adapter = recyclerAdapter
                binding.listdomainRv.layoutManager = GridLayoutManager(this,2)
            }

            "신발 > 여성화" ->{
                val arr = response.result?.womenShoes!!
                val itemlist = ArrayList<LDItem>()
                for(i in arr){
                    itemlist.add(LDItem(
                        i?.categoryTitle,
                        i?.id,
                        i?.price,
                        i?.safeCare,
                        i?.safePay,
                        i?.title,
                        i?.url
                    ))
                }
                val recyclerAdapter = LDAdapter(itemlist)
                recyclerAdapter.notifyDataSetChanged()
                binding.listdomainRv.adapter = recyclerAdapter
                binding.listdomainRv.layoutManager = GridLayoutManager(this,2)
            }

        }
    }

    override fun onGetListsFailure(message: String) {
        showCustomToast(message)
    }
}
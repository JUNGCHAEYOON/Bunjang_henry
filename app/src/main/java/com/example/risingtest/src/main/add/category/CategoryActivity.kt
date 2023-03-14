package com.example.risingtest.src.main.add.category

import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingtest.R
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityCategoryBinding
import com.example.risingtest.src.main.add.category.models.CG1Response

class CategoryActivity : BaseActivity<ActivityCategoryBinding>(ActivityCategoryBinding::inflate),
    CGInterface, PassInterface {

    var text = "전체"

    override fun onResume() {
        super.onResume()

        //리싸이클러뷰 호출
        CGService(this).tryGetCategory()
    }
    
    /*
    대분류 카테고리 호출
    */
    override fun onGetCategorySuccess(response: CG1Response) {
        val result = response.result
        val itemList = ArrayList<CategoryItem>()
        if (result != null) {
            for (i in result) {
                itemList.add(CategoryItem(i?.title.toString(), i?.categoryId!!))
            }
        }
        binding.categoryRv.adapter = CategoryAdapter(this,itemList)
        binding.categoryRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
    override fun onGetCategoryFailure(message: String) {
        showCustomToast(message)
    }

    /*
    대분류 -> 중분류 인터페이스 호출
    */
    override fun onPassCategoryId(categoryId: Int) {
        CGService(this).tryGetMiddle(categoryId.toString())
    }
    // 텍스트뷰 변경
    override fun onPassCategoryTitle(title: String) {
        text += " > " + title
        binding.categoryTvCategory.text = text
    }
    // 중분류
    override fun onGetMiddleSuccess(response: CG1Response) {
        val result = response.result
        val itemList = ArrayList<CategoryItem>()
        if (result != null) {
            for (i in result) {
                itemList.add(CategoryItem(i?.title.toString(), i?.categoryId!!))
            }
        }
        binding.categoryRv.adapter = CategoryAdapter(this,itemList)
        binding.categoryRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
    override fun onGetMiddleFailure(message: String) {
        showCustomToast(message)
    }
    
    /*
    중분류 -> 소분류
    */



}
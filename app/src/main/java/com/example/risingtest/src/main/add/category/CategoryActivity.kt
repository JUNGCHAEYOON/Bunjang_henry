package com.example.risingtest.src.main.add.category

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityCategoryBinding
import com.example.risingtest.src.main.add.AddActivity
import com.example.risingtest.src.main.add.category.models.CG1Response

class CategoryActivity : BaseActivity<ActivityCategoryBinding>(ActivityCategoryBinding::inflate),
    CGInterface, Pass1Interface, Pass2Interface, Pass3Interface {

    // Addactivity 로 넘겨줄 변수
    var category1Id : Int = 0
    var category2Id : Int = 0
    var category3Id : Int = 0
    var category1Title : String = ""
    var category2Title : String = ""
    var category3Title : String = ""

    override fun onResume() {
        super.onResume()
        //뒤로가기
        binding.categoryBtnBack.setOnClickListener {
            finish()
        }

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
    override fun onPass1CategoryId(categoryId: Int) {
        category1Id = categoryId
        CGService(this).tryGetMiddle(categoryId.toString())
    }
    override fun onPass1CategoryTitle(title: String) {
        category1Title = title
        binding.categoryLl1.visibility = View.VISIBLE
        binding.categoryTvCategory1.text = title
    }
    override fun onGetMiddleSuccess(response: CG1Response) {
        val result = response.result
        val itemList = ArrayList<CategoryItem>()
        if (result != null) {
            for (i in result) {
                itemList.add(CategoryItem(i?.title.toString(), i?.categoryId!!))
            }
        }
        binding.categoryRv.adapter = LastAdapter(this,itemList)
        binding.categoryRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
    override fun onGetMiddleFailure(message: String) {
        showCustomToast(message)
    }

    /*
    중분류 -> 소분류
    */
    override fun onPass2CategoryId(categoryId: Int) {
        category2Id = categoryId
        CGService(this).tryGetLast(categoryId.toString())
    }
    override fun onPass2CategoryTitle(title: String) {
        category2Title = title
        binding.categoryLl2.visibility = View.VISIBLE
        binding.categoryTvCategory2.text = title
    }
    override fun onGetLastSuccess(response: CG1Response) {

        showCustomToast(response.result.toString())

        val result = response.result
        val itemList = ArrayList<CategoryItem>()
        if (result != null) {
            for (i in result) {
                itemList.add(CategoryItem(i?.title.toString(), i?.categoryId!!))
            }
        }
        binding.categoryRv.adapter = LastLastAdapter(this,itemList)
        binding.categoryRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
    override fun onGetLastFailure(message: String) {
        showCustomToast(message)
    }
    
    
    /*
    * 소분류
    * */
    override fun onPass3CategoryId(categoryId: Int) {
        category3Id = categoryId
    }
    override fun onPass3CategoryTitle(title: String) {
        category3Title = title
        binding.categoryLl3.visibility = View.VISIBLE
        binding.categoryTvCategory3.text = title

        ApplicationClass.sSharedPreferences.edit().putString("category1",category1Title).apply()
        ApplicationClass.sSharedPreferences.edit().putString("category2",category2Title).apply()
        ApplicationClass.sSharedPreferences.edit().putString("category3",category3Title).apply()
        ApplicationClass.sSharedPreferences.edit().putInt("category1Id",category1Id).apply()
        ApplicationClass.sSharedPreferences.edit().putInt("category2Id",category2Id).apply()
        ApplicationClass.sSharedPreferences.edit().putInt("category3Id",category3Id).apply()

        finish()
    }


}
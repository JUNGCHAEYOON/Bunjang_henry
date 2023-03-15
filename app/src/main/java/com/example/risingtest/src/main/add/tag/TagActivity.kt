package com.example.risingtest.src.main.add.tag

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingtest.R
import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityTagBinding
import com.example.risingtest.src.main.add.tag.models.TagResponse

class TagActivity : BaseActivity<ActivityTagBinding>(ActivityTagBinding::inflate), TagInterface, TagPassInterface {

    //넘겨줄 변수
    var tag1name : String = ""
    var tag2name : String = ""
    var tag3name : String = ""
    var tag1Id : Int = -1
    var tag2Id : Int = -1
    var tag3Id : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        // 뒤로가기
        binding.tagBtnBack.setOnClickListener {
            ApplicationClass.sSharedPreferences.edit().putBoolean("isTagSelected",false).apply()
            finish()
        }

        // 체크버튼
        binding.tagBtnCheck.setOnClickListener {
            finish()
        }

        // 검색버튼
        binding.tagBtnSearch.setOnClickListener {
            val searchTag = binding.tagEtvSearch.text.toString()
            if(searchTag != ""){
                TagService(this).tryGetTag(searchTag)
            }
        }
    }

    // 검색하여 설정가능한 태그 호출
    override fun onGetTagSuccess(response: TagResponse) {
        val result = response.result
        val itemList = ArrayList<TagItem>()
        if (result != null) {
            for(i in result){
                itemList.add(TagItem(i?.id!!, i?.name.toString()))
            }
        }
        binding.tagRv.adapter = TagAdapter(this,itemList)
        binding.tagRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.tagRv.visibility = View.VISIBLE
        binding.tagTvDescribe.visibility = View.GONE
    }

    override fun onGetTagFailure(message: String) {
        showCustomToast(message)
    }

    // 태그, 아이디 전달받음
    override fun onPassTag(tag: String) {
        if(tag != ""){
            if(tag1name == ""){
                tag1name = tag
                binding.tagTvTag1.text = "#" + tag1name
                binding.tagTvTag2.visibility = View.VISIBLE
                binding.tagEtvSearch.setText(null)

                ApplicationClass.sSharedPreferences.edit().putBoolean("isTagSelected",true).apply()
                ApplicationClass.sSharedPreferences.edit().putString("tag1",tag1name).apply()

                ApplicationClass.sSharedPreferences.edit().remove("tag2",).apply()
                ApplicationClass.sSharedPreferences.edit().remove("tag3").apply()
            }else if(tag2name ==""){
                tag2name = tag
                binding.tagTvTag2.text = "#" + tag2name
                binding.tagTvTag3.visibility = View.VISIBLE
                binding.tagEtvSearch.setText(null)
                ApplicationClass.sSharedPreferences.edit().putString("tag2",tag2name).apply()

                ApplicationClass.sSharedPreferences.edit().remove("tag3").apply()
            }else{
                tag3name = tag
                binding.tagTvTag3.text = "#" + tag3name
                binding.tagEtvSearch.hint = ""
                binding.tagEtvSearch.setText(null)
                ApplicationClass.sSharedPreferences.edit().putString("tag3",tag3name).apply()
            }
        }
    }
    override fun onPassTagId(id: Int) {
        if(tag1Id == -1){
            tag1Id = id
            ApplicationClass.sSharedPreferences.edit().putInt("tag1Id",tag1Id).apply()

            ApplicationClass.sSharedPreferences.edit().remove("tag2Id").apply()
            ApplicationClass.sSharedPreferences.edit().remove("tag3Id").apply()
        }else if(tag2Id == -1){
            tag2Id = id
            ApplicationClass.sSharedPreferences.edit().putInt("tag2Id",tag2Id).apply()

            ApplicationClass.sSharedPreferences.edit().remove("tag3Id").apply()
        }else if(tag3Id == -1){
            tag3Id = id
            ApplicationClass.sSharedPreferences.edit().putInt("tag3Id",tag3Id).apply()
        }
    }
}
package com.example.risingtest.src.main.home

import android.app.StatusBarManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.example.risingtest.R
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.FragmentHomeBinding
import com.example.risingtest.src.main.home.banner.HomeAdViewResponse
import com.example.risingtest.src.main.search.SearchActivity
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs
import kotlin.math.min

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
    HomeFragmentInterface {

    // 자동배너가 화면이 바뀌어도 계속 움직이도록 binding 에 접근하다가 NPE 가 발생하는걸 막기위한 변수
    // 완벽한 해결책이라고 보긴 어렵다.
    // onCheck == false 이면 binding 을 호출하지 않는다
    var onCheck = true

    // 광고배너
    var adArrayList = ArrayList<AdArrayList>()
    private lateinit var ad_viewPager: HomeAdViewPagerAdapter
    private lateinit var handler : Handler
    private var currentPosition=0

    // fourteen scroll 시 사용할 변수
    companion object {
        private var scroll_x: Int = 0
        private var scroll_oldx: Int = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 콜랩싱 툴바 스크롤
        toolbarScroll()

        // fourteen 스크롤
        fourteenScroll()

        // 검색화면으로 이동
        binding.homeBtnSearch.setOnClickListener {
            val intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // 광고 자동 스크롤
        handler=Handler(Looper.getMainLooper()){
            setPage()
            true
        }

        // 광고배너 실행
        HomeService(this).tryGetBanners()
    }

    override fun onPause() {
        super.onPause()
        onCheck = false
    }

    /**
     * 상단 상태바 높이 계산 후 DP로 반환
     * @param context Context
     * @return Int(DP값)
     */
    fun getStatusBarHeightDP(context: Context): Int {
        var result = 0
        val resourceId: Int = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimension(resourceId).toInt()
        }
        return result
    }

    // 콜랩싱 툴바 스크롤시
    fun toolbarScroll() {
        binding.homeAb.addOnOffsetChangedListener(object :
            AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                val alpha = min(abs(verticalOffset / 2), 255)
                binding.homeTbShrink.setBackgroundColor(Color.argb(alpha, 255, 255, 255))
                if (alpha > 255 / 2) {
                    binding.homeBtnMenu.setColorFilter(Color.argb(alpha, 0, 0, 0))
                    binding.homeBtnSearch.setColorFilter(Color.argb(alpha, 0, 0, 0))
                    binding.homeBtnAdd.setColorFilter(Color.argb(alpha, 0, 0, 0))
                } else {
                    binding.homeBtnMenu.setColorFilter(Color.argb(alpha, 255, 255, 255))
                    binding.homeBtnSearch.setColorFilter(Color.argb(alpha, 255, 255, 255))
                    binding.homeBtnAdd.setColorFilter(Color.argb(alpha, 255, 255, 255))
                }
            }

        })
    }

    // fourteen 버튼 스크롤시
    fun fourteenScroll() {
        binding.homeHsv.setOnScrollChangeListener { p0, p1, p2, p3, p4 ->
            val view = p0.scrollBarSize
            Log.d("view", view.toString())
            if (p1 > p3) {
                // 오른쪽으로 스크롤 을 때
                scroll_x = p1
                scroll_oldx = p3
            } else if (p1 < p3) {
                // 왼쪽으로 스크롤 했을 때
                scroll_x = p1
                scroll_oldx = p3
            }

            if (scroll_x == 11) {
            } else {
                binding.homeScroll.translationX = scroll_x.toFloat() / 5
            }
        }
    }

    // 광고배너 뷰페이저 만들기!
    fun adViewPager(arr : ArrayList<String>){
        var j = 1
        for(i in arr){
            adArrayList.add(AdArrayList(j.toString(),i))
            j++
        }

        ad_viewPager = HomeAdViewPagerAdapter(this.requireActivity(), adArrayList)
        binding.homeVp.adapter = HomeAdViewPagerAdapter(this.requireActivity(), adArrayList)
        // 넘길 때 효과 제거
        val child = binding.homeVp.getChildAt(0)
        (child as? RecyclerView)?.overScrollMode = View.OVER_SCROLL_NEVER

        // 뷰페이저 넘기는 쓰레드
        val thread = Thread(PagerRunnable())
        thread.start()
    }

    // 뷰페이저 position
    fun setPage() {
        if(currentPosition==9) {
            currentPosition=0
        }
        if(onCheck == true){
            Log.d("GGGGGGGGGGGGGGGGG",binding.homeTvBanner.text.toString())
            binding.homeTvBanner.text = (currentPosition + 1).toString() + "/9 | 모두보기"
            binding.homeVp.setCurrentItem(currentPosition,true)
        }
        currentPosition++
    }

    //2초 마다 페이지 넘기기
    inner class PagerRunnable:Runnable{
        override fun run() {
            while(true){
                try {
                    Thread.sleep(2000)
                    handler.sendEmptyMessage(0)
                } catch (e : InterruptedException){
                    Log.d("interupt", "interupt발생")
                }
            }
        }
    }

    // 배너 불러오는 get함수
    override fun onGetBannersSuccess(response: HomeAdViewResponse) {
        Log.d("TTTTTTTTTTTTTTTTTTTT", response.result?.bannerList?.get(0).toString())
        val arr = ArrayList<String>()
        for(i in response.result?.bannerList!!){
            arr.add(i.toString())
        }

        adViewPager(arr)
    }

    override fun onGetBannersFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}
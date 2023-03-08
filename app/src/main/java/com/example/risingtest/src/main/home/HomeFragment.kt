package com.example.risingtest.src.main.home
import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.risingtest.R
import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.FragmentHomeBinding
import com.example.risingtest.src.login.LoginActivity
import com.example.risingtest.src.main.home.banner.AdArrayList
import com.example.risingtest.src.main.home.banner.HomeAdViewPagerAdapter
import com.example.risingtest.src.main.home.banner.HomeAdViewResponse
import com.example.risingtest.src.main.search.SearchActivity
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs
import kotlin.math.min
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
    HomeFragmentInterface{


    // 배너 작성시 사용할 변수
    var adArrayList = ArrayList<AdArrayList>()
    private lateinit var ad_viewPager: HomeAdViewPagerAdapter
    private lateinit var handler : Handler
    private var currentPosition = 0

    // fourteen scroll 시 사용할 변수
    companion object{
        private var scroll_x : Int = 0
        private var scroll_oldx : Int = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* 홈화면 광고 배너 */
        // handler 선언
//        handler=Handler(Looper.getMainLooper()){
//            setPage()
//            true
//        }
        // 광고배너 실행
        HomeService(this).tryGetBanners()

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
    /* 홈화면 광고 배너 */
    fun adViewPager(){
//        binding.homeTvBanner.bringToFront()

        //리스트 생성
//        adArrayList.add(AdArrayList("1",R.drawable.ic_fourteen_01))


        // 뷰페이저어댑터 생성
        ad_viewPager = HomeAdViewPagerAdapter(this.requireActivity(), adArrayList)
        binding.homeVp.adapter = ad_viewPager

        // 넘기는 효과 없애기
        val child = binding.homeVp.getChildAt(0)
        (child as? RecyclerView)?.overScrollMode = View.OVER_SCROLL_NEVER

        // 뷰페이저 자동 넘기기
        // 오류남 수정필요
        val thread = Thread(PagerRunnable())
        thread.start()
    }

    // 뷰페이저 position
    fun setPage() {
        if(currentPosition==9) {
            currentPosition=0
        }
//        binding.homeVp.setCurrentItem(currentPosition,true)
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

    // 콜랩싱 툴바 스크롤시
    fun toolbarScroll(){
        binding.homeAb.addOnOffsetChangedListener(object:
        AppBarLayout.OnOffsetChangedListener{
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                val alpha = min(abs(verticalOffset/2),255)
                binding.homeTbShrink.setBackgroundColor(Color.argb(alpha,255,255,255))
                if(alpha > 255/2){
                    binding.homeBtnMenu.setColorFilter(Color.argb(alpha,0,0,0))
                    binding.homeBtnSearch.setColorFilter(Color.argb(alpha,0,0,0))
                    binding.homeBtnAdd.setColorFilter(Color.argb(alpha,0,0,0))
                }else{
                    binding.homeBtnMenu.setColorFilter(Color.argb(alpha,255,255,255))
                    binding.homeBtnSearch.setColorFilter(Color.argb(alpha,255,255,255))
                    binding.homeBtnAdd.setColorFilter(Color.argb(alpha,255,255,255))
                }
            }

        })
    }

    // fourteen 버튼 스크롤시
    fun fourteenScroll(){
        binding.homeHsv.setOnScrollChangeListener { p0, p1, p2, p3, p4 ->
            val view = p0.scrollBarSize
            Log.d("view",view.toString())
            if(p1>p3){
                // 오른쪽으로 스크롤 을 때
                scroll_x = p1
                scroll_oldx = p3
            }
            else if(p1<p3){
                // 왼쪽으로 스크롤 했을 때
                scroll_x = p1
                scroll_oldx = p3
            }

            if(scroll_x==11){
            }else {
                binding.homeScroll.translationX = scroll_x.toFloat()/5
            }
        }
    }
    
    // 배너 불러오는 get함수
    override fun onGetBannersSuccess(response: HomeAdViewResponse) {

        for(i in 0..8){
            adArrayList.add(AdArrayList(i.toString(), response.result?.bannerList?.get(i).toString()))
            Log.d("TTTTTTTTTTTTTTTTTTTT",response.result?.bannerList?.get(i).toString())
            Log.d("TTTTTTTTTTTTTTTTTTTT",i.toString())

        }

//        adViewPager()
    }

    override fun onGetBannersFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}
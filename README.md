# 번개장터 안드로이드 클론앱
</br>

* 안드로이드 코틀린으로 작성한 번개장터 클론앱 입니다.
* mvp, 싱글톤 패턴을 사용하였습니다.

# 시연 영상
</br>

[![Video Label](http://img.youtube.com/vi/bGRZJgy2Suk/0.jpg)](https://youtu.be/bGRZJgy2SukI)


</br>

# 2023-03-4 진행상황
</br>

* 기획서상 진행상황
  * 1주차 첫번째 검사 전까지 진행률 (25%)
  * mvp 템플릿 기본 구조 적용하여 applicationClass 를 비롯한 baseClass 작성
  * splash - login - main 으로 이어지는 기본적인 도메인 구성
  * splash 화면
    * 이미지 아이콘 누끼 적용 및 handler 1.5초 적용 완
  * login 화면
    * bottomSheetDialog 작성하여 버튼으로 연결 완료
    * 번개장터 프롤로그 부분 viewPager2 는 작성 완료 하였으나 해당 부분의 폰트를 구하지 못해 아직 빈 프래그먼트를 연결만 해둔상태
    * 전화번호 인증 로그인 부분은 마이페이지 작성이 완료되는 대로 시작할 것
    * 대략적인 도메인 구성이 완료되는대로 카카오톡, 네이버, 페이스북 로그인 추가할 것
  * main 화면
    * bottomNavigation 작성완료, 그러나 해당 부분 아이콘 누끼 딸경우 픽셀이 망가져 다른 대안 강구중, 고화질로 된 캡쳐를 누끼 따야 될듯
    * bottomNavigation 에서 fragment 5개 연결 완료, 그러나 검색/글작성 부분은 액티비티이므로 추후 해당 도메인 다시 구현 할것
    * myPage 화면의 toolbar 생성완료
    * home 화면의 collapsingToolbar 작성중인 상태로 이완시 viewFlipper, 수축시 일반 toolbar 가 보이는 형태로 구현하려 하는데 해당 부분의 이미지 소스를 구하는것에 어려움이 있음
    

* 이슈
  * BottomNavigation 의 아이콘 소스 구하기
  * homeFragment 의 collapsingToolbar 이미지 소스 구하기
  * 아직 dimens 로 여러 수치들을 체계적으로 관리하는것에 어려움이 있으므로 main 화면부터는 개발을 들어가기전에 구체적인 화면내부의 여러 절대값들을 미리 정리하고 시작해야 할 듯
  
* 내일 할것
  * homeFragment collapsingToolbar 완성
  * myPage 화면 구성 및 myPage 내부 3개의 fragment로 구성된 tapLayout 구성
  * 전화번호인증 로그인 구현
</br>

# 2023-03-5 진행상황
</br>

* 기획서상 진행상황
  * 1주차 첫번째 검사 전까지 진행률 (50%)
  * splash - login 화면 유사도 높게 화면 구현완료
  * 이미지 소스 고화질로 누끼따서 원본과 유사하게 교체중
  * 마이페이지 화면 70퍼센트 구현완료

* 이슈
  * dimens 이름 설정시 작명에 어려움을 겪고 있음
  * home 화면의 collapsing 툴바에 들어갈 이미지소스를 어떻게 캡쳐해야 할지 고민중
  
* 내일 할것
  * home , menu , mypage 80퍼센트 이상 구현하기
</br>
  
# 2023-03-6 진행상황
</br>

* 기획서상 진행상황
  * 1주차 첫번째 검사 전까지 진행률 (60%)
  * mypage 화면 내부 판매상품 부분 뷰페이저 추가완료
  
* 이슈
  * 탭레이아웃에서 탭메뉴를 클릭할경우 해당 탭의 background 를 바꾸고 싶은데 어떻게 구현해야 할지 모르겠음
  * 편두통 이슈
  
* 내일 할 것
  * mypage 에서 넘어갈 수 있는 알림, 공유, 설정, 찜목록 부분 액티비티 작성
  
</br>
  
# 2023-03-7 진행상황
</br>

* 기획서상 진행상황
  * 1주차 피드백완료, 우선순위 홈/등록/결제/로그인 으로 정함, 마이페이지는 후순위로 미룸
  * 현재 홈, 등록 화면 70퍼센트 완성
  
* 이슈
  * 카테고리를 어떻게 구분할지, 메뉴에서 얼마나 많은 종류의 카테고리를 다룰지에 대한 백엔드와의 협의 필요
  * 또한 최종 구현범위를 어디까지 잡을지에 대해 고민중, 마이페이지 구현을 추가하는 쪽으로두고 카테고리는 간소화 할듯

* 내일 할 것
  * 본인인증 로그인 화면 완전구성 및 api 호출 시도
  * home 화면 80퍼센트 이상 구성
  * 등록 화면 추가 

</br>
  
# 2023-03-8 진행상황
</br>

* 기획서상 진행상황
  * 글쓰기 화면 다이얼로그 값을 액티비티로 전달하는 기능 구현완료
  * 로그인, 홈화면의 광고배너 부분 2가지 api 연동 시도중
  
* 이슈
  * jwt토큰을 어떻게 다뤄야 하는지 해매고 있어 템플릿 관련 자료를 다시한번 보는중

</br>
  
# 2023-03-9 진행상황
</br>

* 홈화면 콜랩싱 툴바 구현 완료
* 메뉴 액티비티 구현중
* api 명세 나온것을 토대로 홈화면에서 중첩 리싸이클러뷰 구현중

</br>

# 2023-03-10 진행상황
</br>

* 개인적인 일로인해 하루 지방에 가있느라 아예 작업을 하지 못하였습니다. 

</br>

# 2023-03-11 진행상황
</br>

* 진행상황
  * 홈화면 구현완료(api 연동 완료, 중첩 리싸이클러 구현완료)
  * 등록화면 완벽하게 구성완료, api 명세서 나오는대로 등록기능 구현예정

* 이슈
  * 홈화면 스크롤시 우선순위 설정을 어떻게 줘야할지 애매함
  
* 내일할 것
  * 홈화면 왼쪽상단 메뉴버튼 액티비티 구현(api 연동)
  * 마이페이지 api 연동 구현시작


</br>

# 2023-03-12 진행상황
</br>

* 진행상황
  * 홈화면 구현완
  * 등록화면 구성완료, api 만 연결예정
  * 개별 상품 불러오기 구현완료, 결제 api 연결 예정
  
* 이슈
  * 뷰페이저1 어댑터 구현시 container 관련 함수 한줄을 누락하여 시간을 매우소모
  * 상품조회시 레이아웃 하단이 짤리는 현상있어 관련 부분 수정중
  
  
</br>

# 2023-03-13 진행상황
</br>

* 상세정보 페이지 api 연동중 내일중 구현완료 예정
* 등록, 결제 부분 화면 구성중, 화요일 저녁까지 최소요구사항 구현완료 가능할듯
* 2차 검사시 지적받았던 gui 자잘한부분 수정 및 마이페이지 구성시도 예정

</br>

# 2023-03-14 진행상황
</br>

* 등록에서 카테고리 불러오기 기능 구현완료
* 태그 불러오기 기능 구현중, 완료되는 대로 등록기능 구현할것 api 만 호출하면 끝
* 결제기능 구현중
* 상세정보 페이지 추가 구현

</br>

# 2023-03-15 진행상황
</br>

* 등록 api 연결중 많은 오류 발생하여 해결중.. 
* 내일까지 결제기능까지 구현후 제출 예정

</br>

# 2023-03-16 진행상황
</br>

* 등록 api 연결 완료
* 결제 api 연결 완료

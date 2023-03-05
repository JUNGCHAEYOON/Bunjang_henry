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

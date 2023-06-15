# keyword_news_project
> ### 키워드 뉴스 프로젝트(23.08.09 기한)

## 요구사항
### 디자인
<img width="637" alt="1" src="https://github.com/wjdgus3608/keyword_news_project/assets/31335823/6ff3b1aa-a479-4b2d-ade0-bf45ed3aaddc">
<img width="426" alt="2,png" src="https://github.com/wjdgus3608/keyword_news_project/assets/31335823/0cdd6122-895a-4baa-82b1-f46e52f8ea6a">
<img width="782" alt="3" src="https://github.com/wjdgus3608/keyword_news_project/assets/31335823/edb8adfe-4a00-4a68-be4c-fba886e73f56">  
   
![4](https://github.com/wjdgus3608/keyword_news_project/assets/31335823/bca72b8d-1174-4f39-b9ad-bdaacc9f65de)
![5](https://github.com/wjdgus3608/keyword_news_project/assets/31335823/4871c843-e83e-495c-8ed3-38b74b29c2ce)
![6](https://github.com/wjdgus3608/keyword_news_project/assets/31335823/3105e22f-27fa-4426-843b-cd2b3fa1aa03)
![7](https://github.com/wjdgus3608/keyword_news_project/assets/31335823/ba0d2d1c-40a7-4c5b-9fd4-1712e729b0a7)
![8](https://github.com/wjdgus3608/keyword_news_project/assets/31335823/9f4770e8-8ed1-4266-955a-f26651b0dcae)

### 구현 항목
#### 화면
   - 뉴스 리스트 화면(메인)
   - 뉴스 상세(웹뷰?)
   - 대표 키워드 관리 화면(팝업)
   - 시간 설정 화면(팝업)
   - 포함 키워드 관리 화면(팝업)
   - 제외 키워드 관리 화면(팝업)
   - 

#### 기능
   - 네이버 api 통신(실시간, 배치)
   - api 데이터 후처리
   - 앱 푸시
   - 

## 유의사항  
   - 일정 시간, 주기마다 네이버 뉴스 api를 콜하고 푸시 알림 기능 필요
   - 푸시 알림을 위해 안드로이드 앱 백그라운드 실행 사전조사 필요
   - 시간설정은 해당 시간에 등록된 뉴스만 보여주도록 설정
   - 기사 클릭시 광고 송출 요구함(구글 광고 api 사전조사 필요)
   - 플레이스토어 등록 요구함
   - 네이버 api 일 25,000건임(고객도 알고있음)
   - 고객이 api 제한수를 초과할 경우를 우려하여 제한수 초과시 apikey 자동대체 방법을 제안함
   - 프리미엄 전환 아이콘, 회원 관리에 대한 언급이 없었음....
## 역할분담


# 가계부 API

## 기술 스택
- Kotlin
- Spring boot
- Spring Data JPA
- Redis
- MySQL:5.7
- Docker / docker-compose
- Json web token
## 설명
- 모든 기능에 대한 단위 테스트를 진행하지는 못했으나 Service 우선으로 작성하였습니다.
- Redis를 활용해 토큰 로그아웃을 구현하였습니다.
  - 로그아웃 요청 시 해당 토큰은  Redis에 저장됩니다.
  - 해당 데이터의 TTL은 현재시간으로 부터 토큰 만료까지의 차이입니다.
### 실행
- 모든 컨테이너 실행  
`./gradlew build && docker-compose up`    
- 모든 컨테이너 백그라운드 실행  
`./gradlew build && docker-compose up - d`

- 데이터베이스만 실행  
`docker-compose up database`  
- 데이터베이스만 백그라운드 실행  
`docker-compose up -d database`

### 로그인
로그인 API를 호출해 로그인을 수행합니다.  
> * 테스트 계정  
> userid : user1@gmail.com  
> password : 1234

## API 설계
### USER :  HomeController
| Function | Method |URI | Description |
| -------- | --- |--- | ----------- |
| 회원가입 | POST | /register | - |
| 이메일 중복 검사 | POST | /check-email | - |
| 로그인 | POST | /sign-in | - |
| 로그아웃 | GET | /sign-out | - |

### USER : UserController
| Function | Method |URI | Description |
| -------- | --- |--- | ----------- |
| 유저 정보 조회 | GET | /user | - |

### RECEIPT : ReceiptController
| Function | Method |URI | Description |
| -------- | --- |--- | ----------- |
| 기록 상세 조회| GET | /receipts/{receipt_id} | - |
| 일별 기록 조회 | GET | /receipts/daily?date={date} | date: yyyy-mm-dd|
| 월별 기록 조회 | GET | /receipts/monthly?month={month} | month: yyyy-mm |
| 기록하기 | POST | /receipts |-|
| 기록 수정 | PUT | /receipts/{receipt_id} |-|
| 기록 삭제 | DELETE | /receipts/{rceipt_id} |-|

### TAG : TagController
| Function | Method |URI | Description |
| -------- | --- |--- | ----------- |
| 태그 검색 | GET | /tags | - |
| 태그 추가 | POST | /tags | - |

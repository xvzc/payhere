# 가계부 API

## 설명
### 실행
- 모든 컨테이너 실행  
`docker-compose up`    
`docker-compose up - d` // 백그라운드 실행


- 데이터베이스만 실행  
`docker-compose up database`  
`docker-compose up -d database` // 백그라운드 실행

## API 설계
### USER :  HomeController
| Function | URI | Description |
| -------- | --- | ----------- |
| 회원가입 | POST | /register |
| 이메일 중복 검사| POST | /check-email|
| 로그인 | POST | /sign-in |
| 로그아웃 | DELETE | /sign-out |

### USER : UserController
| Function | URI | Description |
| -------- | --- | ----------- |
| | | |

### CASHBOOK : Cashbook Controller
| Function | URI | Description |
| -------- | --- | ----------- |
| | | |
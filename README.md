# 📅 Schedule Develop

## 0️⃣ 개요
### **프로젝트 개요**
- 일정관리 앱 서버를 만들었습니다.
- 유저와 일정 테이블을 가지며, 기본적인 CRUD 기능이 있습니다.
- local DB에 데이터 저장이 가능합니다.
- [프로젝트 회고 🖋️](https://aggeeeee.tistory.com/263 "트러블 슈팅 포함 상세 기록🖋️")

### **프로젝트기간**
- 2025-03-27 ~ 2025-04-04
  
### **개발 환경** 
- jdk: 17.0.1
- Framework: Spring Boot 3.4.4
- Build Tool: Gradle
- IDE: IntelliJ
 <br/>
 
## 1️⃣ ERD 및 API명세서 작성

### **📌 ERD**
![ERD](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FDpdXW%2FbtsNalDarHK%2FobmHCib8NJKWxzlSVa7vsk%2Ftfile.dat)

### **📌 Schedule API 명세서**
| 기능 | Method | URL | request | response | 상태코드 |
| --- | --- | --- | --- | --- | --- |
| 일정 생성 | POST | /schedules | {   "title" : "제목"   "content" : "내용",   "userId" : "유저식별자"   } | {   "scheduleId" : "식별자",   "title" : "제목"   "content" : "내용",   ""userId" : "유저식별자"   } | **201**   Created |
| 일정 전체 조회 | GET | /schedules | \- | \[   {   "scheduleId" : "식별자",   "title" : "제목",   "content" : "내용",   "createdAt" : "작성일",   "updatedAt" : "수정일"   }   \] | **200**   OK |
| 일정 단건 조회 | GET | /schedules/{scheduleId} | Path Variable | {   "scheduleId" : "식별자",   "title" : "제목",   "content" : "내용",   "createdAt" : "작성일",   "updatedAt" : "수정일"   } | **200**   OK |
| 일정 수정 (내용) | PATCH | /schedules/{scheduleId} | Path Variable   {   "content" : "내용",   } | {   "scheduleId" : "식별자",   "title" : "제목",   "content" : "내용",   "createdAt" : "작성일",   "updatedAt" : "수정일"   } | **200**   OK |
| 일정 삭제 | DELETE | /schedules/{scheduleId} | Path Variable |   | **200**   OK |

### **📌 User API 명세서**
| 기능 | Method | URL | request | response | 상태코드 |
| --- | --- | --- | --- | --- | --- |
| 유저 생성   (회원 가입) | POST | /users | {   "userName" : "유저명"   "password" : "비밀번호",   "email" : "이메일"   } | {   "userId" : "유저식별자",   "userName" : "유저명",   "email" : "이메일",   "createdAt" : "생성일"   } | **201**   Created |
| 유저 전체 조회 | GET | /users | \- | \[   {   "userId" : "유저식별자",   "userName" : "유저명",   "email" : "이메일",   "createdAt" : "생성일"   }   \] | **200**   OK |
| 유저 단건 조회 | GET | /users/{userId} | Path Variable | {   "userId" : "유저식별자",   "userName" : "유저명",   "email" : "이메일",   "createdAt" : "생성일"   } | **200**   OK |
| 유저 수정   (비밀번호 변경) | PATCH | /users/{userId} | Path Variable   {   "userName" : "유저명"   "oldPassword" : " 기존비밀번호",   "newPassword" : "바꾸는 비밀번호"   } | {   "userId" : "유저식별자",   "userName" : "유저명",   "email" : "이메일",   "updatedAt" : "수정일"   } | **200**   OK |
| 유저 삭제 | DELETE | /users/{userId} | Path Variable | \- | **200**   OK |
| 유저 로그인 | GET | /users /login | {   "email" : "이메일",   "password" : "비밀번호"   } |   | **200** 로그인 성공      **401** 비밀번호가 일치하지 않습니다. |
 <br/>
 
## 2️⃣ 기능 소개
### **🧩 USER CRUD**
- **유저 생성**: userName,passWord,email로 유저 생성이 가능하며, email은 중복이 불가능합니다.
- **전체 유저 조회**: 등록된 전체 유저 조회가 가능합니다. 유저가 없을 시 빈 리스트가 반환됩니다.
- **유저 비밀번호 수정**: 기존 비밀번호와 신규 비밀번호를 비교하여 비밀번호 수정이 가능합니다. 수정시 수정일이 자동 업데이트 됩니다.
- **유저 삭제**: userId를 입력받아 유저를 삭제합니다.

### **🧩 SCHEDULE CRUD**
- **일정 생성**: title, content, userId를 입력받아 일정을 생성합니다.
- **전체 일정 조회**: 등록된 전체 일정을 조회합니다. 일정이 없 시 빈 리스트가 반환됩니다.
- **일정 내용 수정**: content 수정이 가능하며, 수정시 수정일이 자동 업데이트 됩니다.
- **일정 삭제**: scheduleId를 활용하여 일정을 삭제합니다.

### **🧩 로그인(인증)**
- 이메일과 비밀번호를 입력받은 뒤, Cookie/Session을 활용해 로그인 정보를 저장합니다.
- 유저 생성과 로그인 요청은 인증 없이도 사용 가능합니다.
- 그 외 모든 CRUD는 로그인 후 접근 가능합니다.
 <br/>
 
## 3️⃣ 트러블 슈팅
### 💥 [티스토리 링크 연결](https://aggeeeee.tistory.com/259)   
- 생성자에 final 필드 초기화를 하지 않음
- findByEmail()사용 시 선언하지 않아 생긴 오류
- 세션에서 사용한 키를 일치시키지 않아 로그인 처리가 되지 않음

# Autocrypt
> Spring boot, Spring Security, Java, MySQL

<br>

## 요구사항
### 기능 요구사항
- ( 전체 사용자 이용 가능 )
  - 로그인
  - 회원가입
    - 아이디, 비밀번호, 별칭 필수 포함
- ( 로그인한 사용자만 이용 가능 )
  - 게시글 작성
  - 게시글 수정
  - 게시글 삭제
  - 게시글 잠금설정
  - 타사용자의 잠금 게시글을 제외한 전체 게시글 조회

<br>

## API Spec
|FUCNTION|METHOD|URI|PARAM|BODY|
|--------|------|---|-----|----|
|로그인|POST|/login|-|email, password|
|회원가입|POST|/member|-|email, password, nickname|
|게시글작성|POST|/post|-|content|
|게시글수정|PATCH|/post/{id}|postId|content|
|게시글삭제|DELETE|/post/{id}|postId|-|
|게시글잠금|POST|/post/{id}|postId|-|
|전체게시글조회|GET|/posts||-|

<br>

## 실행 방법
### 데이터베이스
(MySQL Workbench 8.0 기준)
1. Workbench 왼쪽 상단 원통모양을 눌러 autocrypt 명칭의 스키마 생성

### 프로젝트
(intelliJ Community Edition IDE 기준)
1. git clone
2. intellij 실행 후 open project
3. src/main/resources/application.yml의 datasource(url, username, password)부분을 테스터의 환경에 맞게 수정
4. src/main/java/com/noh/autocrypt/AutocryptApplication.java의 실행 버튼(녹색 삼각형)을 눌러 서버 실행
5. localhost:8080으로 접속

<br>

## 테스트 시나리오
* API Spec을 참고하여 테스트 해주세요 
  
|테스트 항목|절차|사전조건|기대결과|
|--------|----|----|------|
|회원가입|1. email, password, nickname을 입력하여 회원가입한다 |-|회원가입이 완료된다|
|로그인|1. email과 password를 입력하여 로그인한다 |-|로그인이 완료된다|
|게시글 작성|1. 로그아웃한다 <br> 2. 게시글을 작성한다 <br> 3. 로그인한다 <br> 4. 게시글을 작성한다|로그인|2. 접근이 불가능하다 <br> 4. 게시글이 작성된다|
|게시글 수정|1. 작성자 이외 계정으로 로그인한다 <br> 2. 게시글을 수정한다 <br> 3. 게시글 작성자 계정으로 로그인한다 <br> 4. 게시글을 수정한다 |게시글 작성|2. 콘솔에 "본인 게시글만 수정 가능합니다." 에러가 출력된다 <br> 4. 게시글이 수정된다|
|게시글 삭제|1. 작성자 이외 계정으로 로그인한다 <br> 2. 게시글을 삭제한다 <br> 3. 게시글 작성자 계정으로 로그인한다 <br> 4. 게시글을 삭제한다 |게시글 작성|2. 콘솔에 "본인 게시글만 삭제 가능합니다." 에러가 출력된다 <br> 4. 게시글이 삭제된다|
|게시글 잠금|1. 작성자 이외 계정으로 로그인한다 <br> 2. 게시글 상태를 변경한다 <br> 3. 게시글 작성자 계정으로 로그인한다 <br> 4. 게시글 상태를 변경한다 |게시글 작성|2. 콘솔에 "본인 게시글만 상태 변경 가능합니다." 에러가 출력된다 <br> 4. 게시글 잠금이 완료된다|
|게시글 조회|1. 잠금 게시글 작성자 이외 계정으로 로그인한다 <br> 2. 전체 게시글을 조회한다 <br> 3. 잠금 게시글 작성자 계정으로 로그인한다 <br> 4. 전체 게시글을 조회한다 |게시글 작성, 게시글 잠금|2. 잠금 게시글을 제외한 게시글을 조회할 수 있다 <br> 4. 잠금 게시글을 포함한 게시글을 조회할 수 있다|

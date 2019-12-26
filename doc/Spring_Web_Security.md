# 파일 업로드 처리

### 1. Security 설정
- web, config, core, taglibs 의존성 추가
- web.xml에 필터, 설정파일 추가
- <u>인증</u>과 <u>권한</u>으로 동작

### 2. 로그인과 로그아웃
- \<intercept-url patten="" access="">
- access 값은 표현식과 권한명을 사용할 수 있음(기본값: use-expressions='true')
- 다양한 접근 제한을 처리하고 싶으면 AccessDeniedHandler를 상속 받아 구현
- 다양한 로그인 성공 후 처리를 AuthenticationSuccessHandler를 상속 받아 구현

### 3. JDBC를 이용한 처리
- 지정된 형식과 기존 데이터베이스를 이용한 방식이 있음
- NoOpPasswordEncoder가 없으므로 PasswordEncoder를 이용하여 직접 구현
- 기존 데이터베이스를 이용하려면 인증을 위한 쿼리를 \<jdbc-user-service users-by-username-query="">
- 권한 확인을 하는 쿼리 \<jdbc-user-service authorities-by-username-query="">

### 4. 커스텀 UserDetailsService
- 이메일등 다양한 정보를 이용하기 위해 직접 UserDetailsService 구현
- User 클래스를 상속시켜 UserDetails 타입으로 변환(auth는 GrantedAuthority로 변환해야함)

### 5. JSP에서 시큐리티 사용
- \<sec:authentication property="principal"/>을 이용해 UserDetailsService에서 반환된 객체 사용
- 표현식을 이용해 동적화면 구성 가능(로그인 유무)

### 6. 자동로그인
- xml에서 remember-me태그를 이용해 메모리나, 데이터베이스에서 설정가능
- 쿠키를 데이터베이스에 저장 후 logout때 같이 삭제
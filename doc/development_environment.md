# 개발 환경 설정

**Intellij 182.4892.20 / jre 1.8.0_152 / tomcat 8.5.39 / maven 4.0 / spring 5 / mariadb 10.4** 사용

### 1. 프로젝트 생성 및 구성
- new
- maven 프로젝트
- pom.xml에 다음 라이브러리 
  - spring-context / spring-test / spring-webmvc / aspectjrt / slf4j-api / jcl-over-slf4j / slf4j-log4j12 / log4j 
  - servlet-api / jsp-api / jstl / junit / mariadb-java-client / lombok
- 인텔리제이 lombok 설정
  - file -> setting -> plugins -> browse repositories에서 lombok 검색 후 설치 -> build -> compiler -> anntoation processors -> enable annotation processing 체크
- Tomcat 설정
  - run -> edit configurations -> +클릭 후 tomcat local 추가 -> tomcat 폴더 지정 -> 우측 하단 fix -> artifact 클릭 후 f4 -> 우측 elements 모두 좌측에 추가
<br><br>

### 2. 간략한 스프링 정리
- 경량 프레임워크 
- POJO 기반으로 유연한 작업 가능
- ApplicationContext가 객체(Bean)를 생성하고 관리하여 개발에 용이
- AOP를 지원하여 보안, 로그, 트랜잭션 같은 cross-concern를 모듈로 관리하여 수직적이 아니라 횡단적으로 관리 가능
<br><br>

### 3. 스프링의 동작 순서
1. 스프링 프레임워크가 동작하면 메모리 영역이 할당되고 ApplicationContext 객체가 생성됨
2. root-context.xml에서 정의된 패키지를 스캔하면서 @Component라는 어노테이션이 존재하는 패키지의 클래스의 인스턴스을 생성
3. @Autowired, @Inject같은 어노테이션 설정을 보고 주입 (테스트코드 : [SampleTests.java](../src/test/java/ch01/SampleTests.java))
<br><br>

### 4. DB 연동
- HikariCP 커넥션 풀 사용
- hikariConfig 빈이 만들어진 후 dataSource 빈에 주입 (테스트코드 : [DataSourceTests.java](../src/test/java/ch01/DataSourceTests.java))
<br><br>

### 5. Mybatis 연동
- 기존의 SQL을 그대로 사용가능
- SQLSessionFactory에서 SQLSession을 만듬
- SQLSession : Connection 생성, SQL 전달, 결과 리턴 받음
- log4jdbc-log4j2를 사용하여 SQL을 쉽게 확인 가능 ([log4jdbc.log4j2.properties](../src/main/resources/log4jdbc.log4j2.properties) 와 [applicationContext](../src/main/webapp/WEB-INF/spring-config/applicationContext.xml) 수정)
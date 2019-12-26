# 기본적인 웹 게시물 관리

### 1. 스프링 MVC 프로젝트 구성
- controller : 요청을 받아들여 처리
- service :  비지니스 영역 담당
- domain : VO, DTO (데이터를 담고 있는 객체)
- persistence : Mapper 인터페이스
    - insert
    - read
    - delete
    - update
    
### 2. 비즈니스 계층
- 고객의 요구사항을 반영하는 계층
- @Service : 비즈니스 영역을 담당하는 객체임을 표시
- 인터페이스를 만들고 구현한 클래스를 만듬([BoardServiceImpl](../src/main/java/com/codespring/ch03/service/BoardServiceImpl.java))

### 3. 프레젠테이션 계층
- @Controller 어노테이션을 사용
- @RequestMapping 등을 이용해 URL을 분기하는 구조
- 전달할 값이 있으면 model 객체에 addAttribute를 이용하여 화면 쪽으로 전달
- WAS을 사용하지 않고 mockmvc를 사용하여 테스트
    - mockmvc : 브라우저에서 요청과 응답을 의미하는 객체
- @RequestParam("bno") int bno : bno을 좀 더 명시적으로 처리하기위해 어노테이션 사용

### 4. 화면처리
- controller의 list 메소드에서 list라는 이름으로 전달한 model을 이용하여 jstl을 사용해 출력
- register 후 modal을 띄워 결과를 보여줌.
    - register 후 뒤로가기를 하면 계속 modal이 출력되는데 <u>history객체</u>를 이용해 해결
    
### 5. 페이징(Paging)
- mysql의 LIMIT를 이용하여 페이징 처리
- 페이지의 끝번호를 현재페이지를 이용하여 계산
    - (int) (Math.ceil(pageNum / 10.0) *10)
- 목록으로 돌아갈 때 1페이지로 돌아가는 문제 수정
    - 페이지 번호를 조회페이지로 같이 전달
    - 조회페이지에서 목록페이지로 이동시 @ModelAttribute를 사용하여 데이터를 넘겨줌
    
### 6. 검색 처리
- mybatis의 동적태그를 이용하여 검색조건이 변해도 사용가능하게 만듬
- UriComponentsBuilder를 사용하여 여러 개의 파라미터 정리
- package org.apache.log4j does not exist
    - pom.xml에서 log4j의 <scope>runtime</scope>를 삭제
    
- dispatcher-servlet.xml에 &#60;mvc:annotation-driven/&#62;을 추가 안하니 매핑이 되지않음
    - &#60;context:component-scan&#62; : 서비스에 필요한 빈들을 찾아서 생성
    - &#60;annotation-driven&#62; : 컴포넌트 스캔으로 만들어진 빈을에 uri를 매핑

- 톰캣 버전에 따라 '[' 와 ']'가 허용 안 될 수 있음
    - %5B, %5D로 변경하거나 js에서 encodeURIComponet()사용
    
- 스프링 4.3 부터 단일 파라미터를 받는 생성자의 경우 필요한 파라미터를 자동으로 주입가능.
   파라미터가 1개의 경우 @AllArgsContstructor로 주입가능
   
- Junit으로 Controller Test를 할때 servlet 버전이 3.1 미만에서는 SessionCookieConfig를 찾지 못하는 오류 발생
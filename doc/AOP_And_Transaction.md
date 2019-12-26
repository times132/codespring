# AOP와 트랜잭션

### 1. AOP
- 로그, 예외처리같은 주변 로직
- Proxy : Target을 감싸고 있는 것
- Advice : 처리해야되는 작업
    - 동작위치에 따라 <u>Before, After Returing, After Throwing, After, Around</u>로 구분
- JoinPoint : Target이 가진 메소드들
- Pointcut : Advice에 JoinPoint을 어떻게 결합할지 설정
    - excution() : 메서드 기준
    - within() : 클래스 기준
    - this : 인터페이스를 구현한 객체
    - args() : 특정 파타미터를 가진 대상만
    - annotation : 특정 어노테이션이 적용된 대상만
- Target : 핵심 로직

### 2. 트랜잭션
- 쪼개질 수 없는 하나의 단위 작업
# REST 방식과 Ajax

### 1. @RestController
- 컨트롤러가 REST 방식을 처리한다는 것을 명시
- 기존의 컨트롤러 같이 Model에 데이터를 담아 JSP 같은 뷰로 전달하는 것이 아니라 순수 데이터를 전송
- 데이터를 전달하는 것이므로 정상적인 데이터인지 체크해야함
    - ResponseEntity를 이용하여 HTTP 헤더의 상태를 전달
- @PathVariable : URL의 경로의 일부를 파라미터로 사용
- @RequestBody : 전달된 요청의 내용을 이용해서 파라미터의 타입으로 변환
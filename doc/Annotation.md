# Spring Annotation

### Class level
- @Controller
- @ResponseBody : JSP 같은 뷰로 전달되는 게 아니라 데이터 자체를 전달하기 위한 용도
- @RestController : @Controller와 @ResponseBody를 합쳐놓은 것
- @RequestMapping : 응답을 수용할 http을 명시 함

### Class Field level
- @Autowired : 적합한 이름의 스프링 빈을 자동으로 주입
- @Resource : 지정한 이름의 스프링 빈을 주입
- @Value : 시스템 환경 변수, Java 환경 변수, Spring 환경 변수, 프라퍼티 리소스 상수 등의 값을 주입

### Method level
- @RequestMapping
    - @GetMapping, @PostMapping, @PutMapping, @DeleteMapping : 요청 메소드에 따라 사용
    - @PostMapping(value = "/board", consumes = MediaType.APPLICATION_JSON_UTP8_VALUE, produces = Media.Type.APPLICATION_XML_VALUE)
        - consumes : http 요청 헤더가 application/json;charset=UTF-8 인 것만 처리
        - produces : http 응답 헤더로 application/xml으로 반환
        
### Method argument level
- @RequestParam : http 요청 파라미터를 매핑
- @PathVariable : URL 경로의 일부를 파라미터로 사용
- @RequestBody : http의 요청을 변환하여 담을 아큐먼트를 앞에 명시
- @RequestHeader : http 헤더 정보를 받아옴
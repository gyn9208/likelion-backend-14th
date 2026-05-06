src
└── main
    └── java
        └── com.backend14.week6
            ├── config
            │   └── AppConfig.java
            ├── policy
            │   └── (정책 관련 파일들)
            ├── role
            │   └── (역할 관련 파일들)
            ├── MemberRepository.java (Interface)
            ├── MemberService.java
            ├── MemoryMemberRepository.java
            └── Week6Application.java (Main Entry)

5주차 파일 위치는 java 안에 처음 생성해둔 com.backend 14.week6(루트 패키지) 안에 위치시켜
스프링 부트의 자동 스캔 범위에 모든 소스 코드를 포함시킨다.

AppConfig: 스프링 컨테이너에게 어떤 객체를 생성하고 서로 어떤 식으로 연결할지를 알려주는 설계도

@Configuration: 스프링 프로젝트의 설정 정보를 담고 있음.
스프링 실행시 이 어노테이션이 붙은 클래스를 1순위로 찾아 설정 내용을 읽음.
내부 등록된 Bean들이 싱글톤으로 유지되도록 보장.

@Bean: 메서드가 생성해서 반환하는 객체를 스프링 컨테이너가 관리하는 객체
이 프로젝트의 경우 memberRepository, memberService가 빈의 이름이 됨.

Week6Application에서 스프링 컨테이너를 최초로 생성하고 빈을 찾아서 가져옴.

결국 순수 자바에서 객체를 생성해서 사용할 땐 필요할 때마다 내가 직접 객체를 생성해 사용했지만
스프링 부트에선 컨테이너가 싱글톤으로 관리하며 필요할 때 주입해 주는 방식이 됨.

스프링부트에선 @SpringBootApplication이 붙은 메인 클래스부터 하위 패키지를 모두 훑으며
@Component가 붙은 클래스르 자동으로 빈으로 만듦.
AppConfig 클래스에서 @Bean으로 객체를 생성하도록 지시했기 때문에 충돌이 발생하게 됨.
=> 해결하기 위해서 AppConfig 클래스의 @Bean을 주석처리함.(이렇게 하면 빈 생서을 자동 등록 방식으로 유지시킬 수 있음)

@Service, @Repository라는 표시와 이를 찾아낸 @SpringBootApplication의 스캔 기능 덕분에 자동 등록 방식이 가능
+ 생성자 주입이라는 개념

@Autowired: 스프링 컨테이너가 관리하는 빈들 사이의 의존관계를 자동으로 연결해 주는 역할
보통 생성자 앞에 사용하거나 필드(변수) 앞에 사용,  수정자(Setter) 앞에 사용함.

@SpringBootAplication이 찾은 @Bean들을 컨테이너에 담는다면 @Autowired는 컨테이너 안에 부품들을 꺼내서 서로 연결시켜주는 역할.

생성자가 하나만 있다면 @Autowired 없이도 자동으로 객체를 만들어줌.

@RestController: 이 클래스가 JSON이나 문자열 같은 데이터를 반환하는 REST API용 컨트롤러임을 선언
게다가 @RestController = @Controller + @ResponseBody다.
@Controller는 return "hello";의 경우 hello.html을 찾으려 하지만 @RestController는 "hello"라는 문자열 자체를 브라우저에 뿌려줌.

@Controller: 이 클래스가 웹 요청을 처리하는 컨트롤러임을 명시하고 스프링 빈으로 등록

@ResponseBody: 메서드가 반환하는 값을 HTML 뷰를 찾지 않고 HTTP 응답 본문(Body)에 직접 담아 보냄

@GetMapping("/hello"): 클라이언트(브라우저)가 서버에 GET 방식으로 /hello 경로에 요청을 보내면
아래 hello() 메서드를 실행하라는 것 
=> 사용자가 브라우저 주소창에 특정 주소를 입력하고 엔터를 눌렀을 때, 서버의 어떤 코드를 실행할지 결정하는 이정표와 같음

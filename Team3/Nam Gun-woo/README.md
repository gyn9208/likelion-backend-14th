DTO: 프로세스 간에 데이터를 전달하는 객체. 말 그대로 데이터를 전송하기 위해 사용하는 객체이기에 비즈니스 로직 같은
복잡한 코드없이 순수하게 전달하고픈 데이터만 담겨있음.
(주로 클라이언트와 서버가 데이터 주고 받을 때 사용)

DTO 안에 클래스들의 역할: CreateRequest(새로운 정보 등록), UpdateRequest(저장된 정보 변경), Response(결과를 사용자에게 보여주기용)

각 클래스에 static from() 팩토리 메서드를 만들어라: 객체 생성의 로직을 클래스 내부로 캡슐화하는
유용한 디자인 패턴인 팩토리 메서드. 외부에서 new 키워드를 사용해 값을 채우는 방법이 아닌
데이터를 던져주면 클래스에서 알아서 객체를 생성해주는 것.
(생성 로직이 DTO 안에서 한 번만 정의되기 때문에 관리가 편함 = 깔끔하다)

팩토리 메서드: 객체 생성 로직을 별도 메서드로 분리해 코드 가독성, 재사용성 up

existsByname(): 특정 데이터(이름)을 가진 데이터가 데이터베이스에 존재하는지 확인 후 Boolean을 반환하는 메서드.

save(): 하나의 개체인 데이터베이스(엔티티)를 받아 저장하는 메서드

removeIf(): 조건에 맞는 요소를 간결, 안전하게 삭제하는 메서드

@RestController: 이 클래스가 웹 요청을 처리하는 컨트롤러임을 스프링에 알림
= 데이터를 JSON 형태로 반환

@RequestMApping("/members"): 이 컨트롤러의 모든 API 경로 /members로 시작하도록 설정
ex) http://localhost:8080/members/@@@

@PostMapping("/lions"): HTTP POST 메서드로 /members/lions 주소에 들어오는 요청을 이 메서드가 처리하도록 연결

@RequestBody: HTTP 요청의 본문에 담긴 JSON 데이터를 LionCreateRquest 객체로 자동 변환해서 넣어줌
RequestBody가 없으면 request 안에 필드가 빈 상태가 됨.
(클라이언트는 보통 데이터를 JSON이라는 텍스트 형식으로 보냄 But. 자바는 JSON 직접 이해 못하고 객체를 사용해야 함)

ResponseEntity는 Spring MVC에서 HTTP 요청에 대한 응답을 제어하는 데 사용하는 클래스. 이 클래스는 Response Body, Header, Status Code를 포함할 수 있어, 세밀한 응답 관리를 가능하게 함

Response Status Code 설정: ResponseEntity 객체를 생성할 때, HTTP Status Code를 명시적으로 제공.
API의 Response를 보다 명확하게 표현할 수 있게 해주며, 클라이언트에게 유용한 정보를 제공.

ResponseEntity<StaffResponse>: API 호출시 Conflict냐 Created냐를 담고 있는 StaffResponse 객체 안에 응답 데이터가 들어있다는 걸 명시.

HTTP 응답 상태 코드는 특정 HTTP 요청이 성공적으로 완료되었는지 알려준다
ex) 409 Conflict, 201 Created, 404 found, 200 OK 등

ResponseEntity<?>에서 <?>는 무엇이든 들어갈 수 있다는 뜻

@PathVariable: 경로(Path)에 있는 변수(Variable)이라는 뜻
브라우저 주소창에 입력하는 URL 경로의 일부를 자바 메서드의 파라미터로 가져올 때 사용
고유한 ID의 게시글을 주소에 적힌 값을 보고 그 값을 서버 코드로 가져오려고 할 때 필요(원하는 고유 글을 띄우고 싶을 때)
vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
동작 원리 (이미지로 이해하기)
사용자: http://localhost:8080/members/James 주소로 요청을 보냄.

스프링: 주소의 마지막 부분인 James를 확인.

매핑: {name} 자리에 James가 있으니, @PathVariable String name에 "James"를 대입함.

결과: 메서드 안에서 name 변수를 사용해 "James"라는 데이터를 DB에서 찾을 수 있게 됨.

instanceof: 객체 타입을 확인하는 연산자
패턴매칭
1. parent instanceof Parent : 부모가 본인 집을 찾았으니 true
2. child instanceof Parent : 자식이 상속받은 부모 집을 찾았으니 true (상속을 받았으니 자기 집이라 해도 무방하다?)
3. parent instanceof Child : 부모가 자식 집을 찾았으니 false (자식 집은 자식 집이지 부모 집은 아니니까)
4. child instanceof Child : 자식이 본인 집을 찾았으니 true
출처: https://mine-it-record.tistory.com/120 [나만의 기록들:티스토리]

@PutMapping 또한 @PostMapping과 마찬가지로 HTTP 메시지 바디 부분에 JSON 형식으로 데이터를 전송하기 때문에, 메서드 파라미터 앞 부분에 @RequestBody 어노테이션을 사용해야 함.

@PutMapping: 이미 존재하는 데이터를 수정(Update)할 때 사용함(전체 데이터를 갈아끼울 때)
/{name}: 주소창에 들어오는 이름을 변수로 취급

notContent(): 상태 코드 204
=>삭제 작업이 문제없이 완료됐다는 뜻

build(): 데이터(body) 넣지 않고 그대로 응답 상자를 완성시키기
















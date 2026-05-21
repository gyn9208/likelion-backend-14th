MySQL 연결
-ddl-auto=create - 애플리케이션 시작 시 기존 테이블을 삭제하고 새로 생성한다
-show-sql=true - Hibernate가 실행하는 SQL을 콘솔에 출력한다
-format_sql=true - SQL을 보기 좋게 포맷팅한다

MemoryMemberRepository.java 삭제
why? JpaRepository가 대체
그동안은 진짜 데이터베이스가 없어서 컴퓨터 메모리(HashMap 등)에 임시로 회원 정보를 저장하는 방식
But. 이제는 MySQL 디스크에 안전하게 저장해 주는 JpaRepository 인터페이스 사용
컴퓨터 껐다 켜도 정보 보존 가능.

AppConfig.java 삭제
why? 수동 Bean 등록이 불필요
=> 이전 구조에서는 어떤 클래스가 어떤 리포지토리를 쓸지 자바 코드로 일일이 지정해 주는 결재판(AppConfig)이 필요
But. 이제는 이제 스프링 부트와 JPA를 쓰면 @Service, @Repository 같은 어노테이션만 붙여도 스프링이 알아서 조립(자동 의존성 주입)

domain/role/ 패키지 전체 삭제
why? Member 엔티티가 대체
=>데이터베이스 중심의 JPA 아키텍처로 넘어가면서, 이 구조들을 Member 클래스(엔티티) 내부 필드(예: String role이나 @Enumerated 등)로 합치거나 단순화하여 흡수시켰기 때문에 필요가 없어진 것
DB는 '객체'라는 개념을 모르고, 오직 바둑판 모양의 '테이블'만 안다.

JPA 아키텍처 (데이터베이스 중심의 설계): 처음부터 "이 자바 코드는 결국 MySQL 테이블이 될 몸이다!"라는 것을 인정하고 들어가는 설계

@Entity: 이 클래스가 데이터베이스(MySQL)의 테이블과 1:1로 매칭되는 '엔티티'임을 선언
스프링이 켜질 때 이 클래스 구조를 보고 'member'라는 테이블을 자동으로 만든다.

@Id: 데이터베이스 테이블의 '기본키(Primary Key, PK)'를 지정. (중복 없는 식별 번호)

@GeneratedValue(...IDENTITY): 번호 생성 전략을 DB에 위임. (MySQL의 Auto Increment 기능처럼 데이터가 들어갈 때마다 1, 2, 3... 알아서 1씩 증가)

@Enumerated(EnumType.STRING): Enum(열거형) 상수를 DB에 저장할 때 숫자가 아닌 '문자열(LION, STAFF)' 그대로 저장하도록 설정

extends JpaRepository<Member, Long>
뒤에 붙인 <Member, Long>은 각각 <연결할 엔티티 클래스, 그 엔티티의 @Id(PK) 타입>을 뜻
=>데이터 저장(save), 아이디로 찾기(findById), 전체 조회(findAll) 같은 핵심 CRUD 메서드를 스프링이 알아서 구현
인터페이스만 만들었는데 구현체까지 자동으로 만들어지는 마법

JpaRepository를 상속하면 save(), findById(), findAll(), deleteById(), existsById() 등이 자동 제공

JpaRepository의 실체(구조)는 프로젝트를 만들 때 추가했던 외부 라이브러리(Spring Data JPA 주머니) 안에 이미 들어있음

findByName(String name): "이름을 조건으로 회원을 찾아줘!"라는 뜻이 되어 내부적으로 SELECT * FROM member WHERE name = ? 라는 SQL 쿼리가 자동으로 날아감
결과가 없을 수도 있으니 Optional 붙여주기

Optional은 데이터를 날것 그대로 주지 않고, Optional이라는 투명한 상자에 한 번 감싸서 반환
데이터가 존재할 때: Optional 상자 안에 Member 객체가 쏙 들어가 있습니다.
데이터가 없을 때: Optional.empty()라는 빈 상자를 돌려줍니다
=> null을 직접 다루지 않아도 된다.

existsByName(String name): "이 이름의 회원이 이미 존재하는지 여부만 true/false로 알려줘!"라는 뜻이 되어 중복 체크

RoleType.LION, RoleType.STAFF: Enum(열거형) 상수, 테이블에 들어온 데이터가 아기사자인지 운영진인지 구분하는 방법

studentId와 position 순서 다른 이유:
아까 Member 엔티티의 생성자를 만들 때 매개변수 순서를 아래와 같이 고정해 둠.
Member(이름, 전공, 기수, 파트, 역할, 학번, 직책)
생성자를 쓸 때는 이 정의된 순서를 무조건 칼같이 지켜야 함
Staff 생성 역할(RoleType) 다음에 학번이 와야 하므로 일단 null을 넣고, 마지막 순서인 직책 자리에 request.getPosition()을 넣은 것
=>하나의 Member 생성자 규격에 맞춰서 데이터를 넣다 보니, 나한테 필요 없는 필드 칸에는 null을 채워서 순서를 맞춰주고 있는 것

MemberResponse: 클라이언트(웹/앱)에 회원의 정보를 응답할 때 사용하는 데이터 전송 객체(DTO)
엔티티가 Member 하나로 통합되었으므로 응답도 하나로 맞추는 것

member.getRoleType().getDisplayName(): 
1. Member 엔티티 객체에서 역할 정보를 꺼내옵니다.
그러면 아까 저장했던 RoleType.LION 이나 RoleType.STAFF 같은 Enum 상수가 반환
2. 꺼내온 Enum 상수(예: LION)에 대고 "너 아까 상자 안에 품고 있던 한글 이름 좀 줘봐" 하고 메서드를 호출하는 것

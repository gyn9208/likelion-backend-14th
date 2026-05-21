package com.backend14.service;

import com.backend14.domain.Member;
import com.backend14.domain.RoleType;
import com.backend14.dto.LionCreateRequest;
import com.backend14.dto.LionUpdateRequest;
import com.backend14.dto.StaffCreateRequest;
import com.backend14.dto.StaffUpdateRequest;
import com.backend14.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 멤버 관련 비즈니스 로직을 처리하는 역할 (서비스)
 *
 * [개선됨] 의존성 주입(DI) 적용
 * - Repository를 직접 생성하지 않고, 생성자를 통해 외부에서 주입받는다
 * - Repository 인터페이스에만 의존하므로 구현체가 바뀌어도 이 코드는 수정 불필요
 * - final 키워드로 불변성 보장
 * 스프링이 켜질 때 자동으로 빈(Bean)으로 등록되어 관리
 */
@Service
public class MemberService {
    // 인터페이스에 의존 (구현체에 의존하지 않음)
    private final MemberRepository repository;

    // 생성자를 통해 의존성 주입
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    //lion 객체 등록하기
    public Member createLion(LionCreateRequest request){
        //이름 중복 검사
        if(repository.existsByName(request.getName())){
            return null;
        }

        //lion 객체 생성(Member 엔티티 객체)
        Member member = new Member(
            request.getName(),
            request.getMajor(),
            request.getGeneration(),
            request.getPart(),
            RoleType.LION,
            request.getStudentId(),
            null
        );
        //저장하기
        //저장한 lion 객체 반환
        return repository.save(member);

    }
    //staff 객체 등록하기
    public Member createStaff(StaffCreateRequest request){
        Member member = new Member(
            request.getName(),
            request.getMajor(),
            request.getGeneration(),
            request.getPart(),
            RoleType.STAFF,
            null,
            request.getPosition()
        );
        //이름 중복 검사
        if(repository.existsByName(request.getName())){
            return null;
        }
        // DB에 저장하고, 영속화되어 ID가 채워진 객체를 최종 반환
        return repository.save(member);

    }

    //lion객체 수정하기
    public Member updateLion(Long id, LionUpdateRequest request){
        //고유 식별 번호(id)로 DB에서 회원 조회
        //없으면 Optional에서 null 꺼내기
        Member member = repository.findById(id).orElse(null);
        //없는 회원이라면 null 반환
        if(member == null){
            return null;
        }
        //엔티티 내부 수정용 메서드 호출하여 값 변경
        //공통 수정
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        //lion 전용 학번 수정
        member.updateStudentId(request.getStudentId());
        //변경된 엔티티 객체 DB에 저장
        return repository.save(member);
    }
    //staff객체 수정하기
    public Member updateStaff(Long id, StaffUpdateRequest request){
        //고유 식별 번호(id)로 DB에서 회원 조회
        //없으면 Optional에서 null 꺼내기
        Member member = repository.findById(id).orElse(null);

        //없는 회원이라면 null 반환
        if(member == null) {
            return null;
        }
        //엔티티 내부 수정용 메서드 호출하여 값 변경
        //공통 수정
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        //staff 전용 직책 수정
        member.updatePosition(request.getPosition());
        //변경된 엔티티 객체 DB에 저장
        return repository.save(member);
    }
    //이름으로 검색
    public Member searchByName(String name) {
        //리포지토리에 커스텀하게 정의했던 findByName(name) 규칙을 활용
        // 결과가 비어있을 수도 있으므로 안전하게 .orElse(null)로 처리하여 조회
        return repository.findByName(name).orElse(null);
    }
    // 전체 조회
    public List<Member> getAllMembers() {
        return repository.findAll();
    }
    // ID로 조회
    public Member findById(Long id) {
        // 결과가 비어있을 수도 있으므로 안전하게 .orElse(null)로 처리하여 조회
        return repository.findById(id).orElse(null);
    }
    //ID로 삭제하기
    public boolean deleteMember(Long id){
        if(!repository.existsById(id)){
            return false;
        }
        repository.deleteById(id);
        return true;
    }


}

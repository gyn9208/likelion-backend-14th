package com.backend14.week6.service;

import com.backend14.week6.domain.role.Lion;
import com.backend14.week6.domain.role.Staff;
import com.backend14.week6.dto.LionCreateRequest;
import com.backend14.week6.dto.LionUpdateRequest;
import com.backend14.week6.dto.StaffCreateRequest;
import com.backend14.week6.dto.StaffUpdateRequest;
import com.backend14.week6.repository.MemberRepository;
import com.backend14.week6.domain.role.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 멤버 관련 비즈니스 로직을 처리하는 역할 (서비스)
 *
 * [개선됨] 의존성 주입(DI) 적용
 * - Repository를 직접 생성하지 않고, 생성자를 통해 외부에서 주입받는다
 * - Repository 인터페이스에만 의존하므로 구현체가 바뀌어도 이 코드는 수정 불필요
 * - final 키워드로 불변성 보장
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
    public Role createLion(LionCreateRequest request){
        //lion 객체 생성
        Lion lion = new Lion(
            request.getName(),
            request.getMajor(),
            request.getGeneration(),
            request.getPart(),
            request.getStudentId()
        );
        //이름 중복 검사
        if(repository.existsByName(request.getName())){
            return null;
        }
        //저장하기
        repository.save(lion);
        //저장한 lion 객체 반환
        return lion;
    }
    //staff 객체 등록하기
    public Role createStaff(StaffCreateRequest request){
        Staff staff = new Staff(
            request.getName(),
            request.getMajor(),
            request.getGeneration(),
            request.getPart(),
            request.getPosition()
        );

        if(repository.existsByName(request.getName())){
            return null;
        }
        repository.save(staff);
        return staff;
    }

    //lion객체 수정하기
    public Role updateLion(String name, LionUpdateRequest request){
        //이름으로 멤버 찾기
        if(repository.findByName(name)==null){
            return null;
        }
        //lion 정보 수정
        Lion updated = new Lion(
            name,
            request.getMajor(),
            request.getGeneration(),
            request.getPart(),
            request.getStudentId()
            );
        repository.updateByName(name, updated);
        return updated;
    }
    //staff객체 수정하기
    public Role updateStaff(String name, StaffUpdateRequest request){
        //이름으로 멤버 찾기
        if(repository.findByName(name)==null){
            return null;
        }
        //staff 정보 수정
        Staff updated = new Staff(
            name,
            request.getMajor(),
            request.getGeneration(),
            request.getPart(),
            request.getPosition()
        );
        repository.updateByName(name, updated);
        return updated;
    }
    //이름으로 멤버를 삭제하기
    public boolean deleteMember(String name){
        return repository.deleteByName(name);
    }

    public Role searchByName(String name) {
        return repository.findByName(name);
    }
}

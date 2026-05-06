package com.backend14.week6;

import com.backend14.week6.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @Autowired
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public boolean register(Role member) {
        if (repository.existsByName(member.getName())) {
            return false;
        }
        repository.save(member);
        return true;
    }

    public Role searchByName(String name) {
        return repository.findByName(name);
    }

    public List<Role> getAllMembers() {
        return repository.findAll();
    }

    public boolean isEmpty() {
        return repository.findAll().isEmpty();
    }
}

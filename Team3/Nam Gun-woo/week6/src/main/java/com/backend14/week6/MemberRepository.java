package com.backend14.week6;

import com.backend14.week6.role.Role;

import java.util.List;

/**
 * 멤버 저장소 인터페이스
 *
 * 저장소를 인터페이스로 추상화하면:
 * - 구현체를 자유롭게 교체할 수 있다 (메모리, 파일, DB 등)
 * - Service는 인터페이스에만 의존하므로 구현체가 바뀌어도 수정할 필요 없다
 */
public interface MemberRepository {
    void save(Role member);
    Role findByName(String name);
    List<Role> findAll();
    boolean existsByName(String name);
}

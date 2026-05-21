package com.backend14.repository;

import com.backend14.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 멤버 저장소 인터페이스
 *
 * 저장소를 인터페이스로 추상화하면:
 * - 구현체를 자유롭게 교체할 수 있다 (메모리, 파일, DB 등)
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
    boolean existsByName(String name);
}

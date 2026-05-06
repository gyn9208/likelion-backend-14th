package com.backend14.week6;

import com.backend14.week6.role.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 메모리 기반 멤버 저장소 구현체
 *
 * com.backend14.week6.MemberRepository 인터페이스를 구현한다.
 * 데이터는 List에 저장되며, 프로그램 종료 시 사라진다.
 *
 * 나중에 FileMemberRepository, DatabaseMemberRepository 등
 * 다른 구현체를 만들어 교체할 수 있다.
 */
@Repository
public class MemoryMemberRepository implements MemberRepository {
    private List<Role> members = new ArrayList<>();

    @Override
    public void save(Role member) {
        members.add(member);
    }

    @Override
    public Role findByName(String name) {
        for (Role member : members) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public List<Role> findAll() {
        return members;
    }

    @Override
    public boolean existsByName(String name) {
        for (Role member : members) {
            if (member.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}

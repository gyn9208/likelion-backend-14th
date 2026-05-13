package com.backend14.week6.repository;

import com.backend14.week6.domain.role.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 메모리 기반 멤버 저장소 구현체
 *
 * com.backend14.week6.repository.MemberRepository 인터페이스를 구현한다.
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
    @Override
    public void updateByName(String name, Role member) {
        for (int i = 0; i < members.size(); i++) {
            //members.get(i)로 현재 칸에 있는 멤버를 꺼내서, 그 멤버의 이름(getName())이 우리가 찾고 있는 이름(name)과 같은지 비교
            if (members.get(i).getName().equals(name)) {
                members.set(i, member);
                return;
            }
        }
    }
    @Override
    public boolean deleteByName(String name) {
        //꺼낸 멤버의 name이 사용자가 입력한 name과 같다면 삭제
        return members.removeIf(member -> member.getName().equals(name));
    }
}

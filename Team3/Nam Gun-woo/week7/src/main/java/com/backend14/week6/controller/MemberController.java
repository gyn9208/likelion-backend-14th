package com.backend14.week6.controller;


import com.backend14.week6.domain.role.Lion;
import com.backend14.week6.domain.role.Role;
import com.backend14.week6.domain.role.Staff;
import com.backend14.week6.dto.*;
import com.backend14.week6.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//사용자로부터 HTTP 요청을 받아 서비스 계층에 전달
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    //생성자를 통해서 MemberService 의존성 주입
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    //POST /members/lions 등록
    @PostMapping("/lions")
    public ResponseEntity<LionResponse> createLion(@RequestBody LionCreateRequest request){
        Role lion = memberService.createLion(request);

        if(lion == null){
            //클라이언트에게 409 Conflict 상태 코드 전달
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        //클라이언트에게 201 Created 상태 코드 전달
        return ResponseEntity.status(HttpStatus.CREATED).body(LionResponse.from((Lion) lion));
    }

    //POST /members/staffs 등록
    @PostMapping("/staffs")
    public ResponseEntity<StaffResponse> createStaff(@RequestBody StaffCreateRequest request){
        Role staff = memberService.createStaff(request);

        if(staff == null){
            //클라이언트에게 409 Conflict 상태 코드 전달
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        //클라이언트에게 201 Created 상태 코드 전달
        return ResponseEntity.status(HttpStatus.CREATED).body(StaffResponse.from((Staff) staff));
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getMember(@PathVariable String name){
        Role member = memberService.searchByName(name);
        if(member == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toResponse(member));
    }
    //결과물이 LionResponse 혹은 StaffResponse일 수도 있기에 Object로 설정
    private Object toResponse(Role role) {
        //role이 Lion 클래스로 만들어졌는지 확인(패턴 매칭)
        if (role instanceof Lion lion) {
            return LionResponse.from(lion);
            //role이 Staff 클래스로 만들어졌는지 확인(패턴 매칭)
        } else if (role instanceof Staff staff) {
            return StaffResponse.from(staff);
        }
        //들어온 데이터가 lion staff 둘 다 아니라면 기존 객체 그대로 돌려주기
        return role;
    }
    //Lion 수정 API
    @PutMapping("/lions/{name}")
    public ResponseEntity<LionResponse> updateLion(@PathVariable String name,
                                                   @RequestBody LionUpdateRequest request){
        Role updated = memberService.updateLion(name, request);
        if(updated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(LionResponse.from((Lion) updated));
    }

    //Staff 수정 API
    @PutMapping("/staffs/{name}")
    public ResponseEntity<StaffResponse> updateStaff(@PathVariable String name,
                                                     @RequestBody StaffUpdateRequest request){
        Role updated = memberService.updateStaff(name, request);
        if(updated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(StaffResponse.from((Staff) updated));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteMember(@PathVariable String name){
        boolean success = memberService.deleteMember(name);
        if(!success){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


}

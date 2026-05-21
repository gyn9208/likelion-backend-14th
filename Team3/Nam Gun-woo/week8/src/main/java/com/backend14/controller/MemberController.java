package com.backend14.controller;


import com.backend14.domain.Member;

import com.backend14.dto.*;
import com.backend14.repository.MemberRepository;
import com.backend14.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest request){
        Member member = memberService.createLion(request);

        if(member == null){
            //클라이언트에게 409 Conflict 상태 코드 전달
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        //클라이언트에게 201 Created 상태 코드 전달
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.from(member));
    }

    //POST /members/staffs 등록
    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest request){
        Member member = memberService.createStaff(request);

        if(member == null){
            //클라이언트에게 409 Conflict 상태 코드 전달
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        //클라이언트에게 201 Created 상태 코드 전달
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.from(member));
    }
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers(){
        //stream().map(MemberResponse::from).toList()를 사용해
        //    리스트 안의 알맹이(Member 엔티티)들을 전부 MemberResponse DTO로 하나씩 변환해서 새로운 리스트로 묶음.
        List<MemberResponse> responses = memberService.getAllMembers().stream()
            .map(MemberResponse::from)
            .toList();
        return ResponseEntity.ok(responses);
    }

    //단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id){
        Member member = memberService.findById(id);
        if(member == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MemberResponse.from(member));
    }

    //Lion 수정 API
    @PutMapping("/lions/{name}")
    public ResponseEntity<MemberResponse> updateLion(@PathVariable Long id,
                                                   @RequestBody LionUpdateRequest request){
        Member updated = memberService.updateLion(id, request);
        if(updated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MemberResponse.from(updated));
    }

    //Staff 수정 API
    @PutMapping("/staffs/{name}")
    public ResponseEntity<MemberResponse> updateStaff(@PathVariable Long id,
                                                     @RequestBody StaffUpdateRequest request){
        Member updated = memberService.updateStaff(id, request);
        if(updated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MemberResponse.from(updated));
    }
    //member 삭제
    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id){
        boolean success = memberService.deleteMember(id);
        if(!success){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


}

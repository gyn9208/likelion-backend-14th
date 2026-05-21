package com.backend14.dto;

import com.backend14.domain.Member;

//Member 엔티티 객체를 통째로 넘겨받아 안전하게 MemberResponse DTO 객체로 변환
public class MemberResponse {
  private String name, major, part, position, roleName, studentId;
  private int generation;

  //넘겨 받은 엔티티 Getter 사용해 DTO 필드 채우기
  public static MemberResponse from(Member member) {
    MemberResponse response = new MemberResponse();
    response.name = member.getName();
    response.major = member.getMajor();
    response.generation = member.getGeneration();
    response.part = member.getPart();
    //"DB용 데이터인 LION/STAFF를 화면 표시용 데이터인 "아기사자"/"운영진"으로 번역해서 가져오는 코드
    response.roleName = member.getRoleType().getDisplayName();
    response.studentId = member.getStudentId();
    response.position = member.getPosition();
    return response;
  }
  //

  public String getName() {
    return name;
  }
  public String getMajor() {
    return major;
  }
  public int getGeneration() {
    return generation;
  }
  public String getPart() {
    return part;
  }
  public String getRoleName() {
    return roleName;
  }
  public String getStudentId(){
    return studentId;
  }
  public String getPosition() {
    return position;
  }
}
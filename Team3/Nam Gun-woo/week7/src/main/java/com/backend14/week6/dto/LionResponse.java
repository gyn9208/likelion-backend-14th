package com.backend14.week6.dto;

import com.backend14.week6.domain.role.Lion;

public class LionResponse {
  private String name, major, part, roleName, studentId;
  private int generation;

  public static LionResponse from(Lion lion){
    //빈 객체를 생성
    LionResponse response = new LionResponse();
    //객체 정보를 response 객체에 넣는다.
    response.name = lion.getName();
    response.major = lion.getMajor();
    response.generation = lion.getGeneration();
    response.part = lion.getPart();
    response.roleName = lion.roleName();
    response.studentId = lion.getStudentId();
    //정보를 채워넣은 response 객체를 밖으로 돌려준다.
    return response;
  }
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
  public String getStudentId() {
    return studentId;
  }
}

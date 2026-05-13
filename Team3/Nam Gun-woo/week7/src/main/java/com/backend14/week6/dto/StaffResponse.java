package com.backend14.week6.dto;

import com.backend14.week6.domain.role.Staff;

public class StaffResponse {
  private String name, major, part, position, roleName;
  private int generation;

  public static StaffResponse from(Staff staff) {
    StaffResponse response = new StaffResponse();
    response.name = staff.getName();
    response.major = staff.getMajor();
    response.generation = staff.getGeneration();
    response.part = staff.getPart();
    response.roleName = staff.roleName();
    response.position = staff.getPosition();
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
  public String getPosition() {
    return position;
  }
}

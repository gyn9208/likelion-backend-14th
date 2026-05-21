package com.backend14.dto;

public class StaffUpdateRequest {
  private String major, part, position;
  private int generation;

  public String getMajor() {
    return major;
  }
  public void setMajor(String major) {
    this.major = major;
  }

  public int getGeneration() {
    return generation;
  }
  public void setGeneration(int generation) {
    this.generation = generation;
  }

  public String getPart() {
    return part;
  }
  public void setPart(String part) {
    this.part = part;
  }

  public String getPosition() {
    return position;
  }
  public void setPosition(String position) {
    this.position = position;
  }
}

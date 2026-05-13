package com.backend14.week6.dto;

public class LionUpdateRequest {
  String major, part, studentId;
  int generation;

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

  public String getStudentId() {
    return studentId;
  }
  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }
}

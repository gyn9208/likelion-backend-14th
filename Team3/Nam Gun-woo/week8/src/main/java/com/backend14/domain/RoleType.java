package com.backend14.domain;

public enum RoleType {
  // 1. LION("아기사자"), STAFF("운영진") 값 정의
  LION("아기사자"),
  STAFF("운영진");

  // 2. displayName 필드 추가
  private final String displayName;

  // Enum 생성자
  RoleType(String displayName) {
    this.displayName = displayName;
  }

  // 2. getter 추가
  public String getDisplayName() {
    return displayName;
  }
}

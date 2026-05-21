package com.backend14.domain;

import jakarta.persistence.*;

@Entity
public class Member {
  //데이터베이스 테이블 기본키 지정
  @Id
  //번호 생성 전략을 DB에 위임
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;
  private String name;
  private String major;
  private int generation;
  private String part;

  @Enumerated(EnumType.STRING)
  private RoleType roleType;

  private String studentId;

  private String position;

//  기본 생성자: JPA가 내부적으로 객체를 생성하고 다룰 때 반드시 필요
// 무분별한 외부 생성을 막기 위해 접근 제어자를 'protected'로 제한해 안전하게 보호
  protected Member() {
  }
  //정보 등록
  public Member(String name, String major, int genation, String part,
                RoleType roleType, String studentId, String position){
    this.name = name;
    this.major = major;
    this.generation = genation;
    this.part = part;
    this.roleType = roleType;
    this.studentId = studentId;
    this.position =position;
  }

  // 기본 정보 변경
  public void updateInfo(String major, int generation, String part) {
    this.major = major;
    this.generation = generation;
    this.part = part;
  }
  //학번 정보 수정
  public void updateStudentId(String studentId) {
    this.studentId = studentId;
  }
  //직급 정보 수정
  public void updatePosition(String position) {
    this.position = position;
  }

  //값을 안전하게 '조회(Read)'만 할 수 있도록 Getter만 열어둠
  public Long getId() {
    return id; }
  public String getName() {
    return name; }
  public String getMajor() {
    return major; }
  public int getGeneration() {
    return generation; }
  public String getPart() {
    return part; }
  public RoleType getRoleType() {
    return roleType; }
  public String getStudentId() {
    return studentId; }
  public String getPosition() {
    return position; }
}

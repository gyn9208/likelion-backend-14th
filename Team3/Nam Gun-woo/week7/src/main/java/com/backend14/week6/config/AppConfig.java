package com.backend14.week6.config;

import com.backend14.week6.repository.MemberRepository;
import com.backend14.week6.service.MemberService;
import com.backend14.week6.repository.MemoryMemberRepository;

//@Configuration
public class AppConfig {

  //추상화 연결
//  @Bean
  public MemberRepository memberRepository(){
    return new MemoryMemberRepository();
  }

  //의존관계 주입(DI)
//  @Bean
  public MemberService memberService(){
    return new MemberService(memberRepository());
  }
}

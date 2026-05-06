package com.backend14.week6.config;

import com.backend14.week6.MemberRepository;
import com.backend14.week6.MemberService;
import com.backend14.week6.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

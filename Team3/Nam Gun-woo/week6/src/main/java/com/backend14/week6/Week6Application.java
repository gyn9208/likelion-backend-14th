package com.backend14.week6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Week6Application {

  public static void main(String[] args) {
    //스프링 컨테이너 생성하고 반환
    ApplicationContext applicationContext = SpringApplication.run(Week6Application.class, args);

    //컨테이너에서 빈을 찾아 가져옴
    MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

    System.out.println("meberService 객체: "+memberService);

  }

}

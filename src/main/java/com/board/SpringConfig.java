package com.board;

import com.board.repository.*;
import com.board.service.MemberService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;


@Configuration
@ComponentScan
public class SpringConfig {
    
    //private final MemberRepository memberRepository;
    
    /*public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/
    
    
    /*@Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }*/
}
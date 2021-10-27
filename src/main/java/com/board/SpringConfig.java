package com.board;

import org.springframework.beans.factory.annotation.Autowired;

import com.board.repository.MemberRepository;
import com.board.repository.UserRepository;
import com.board.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {
    
    private EntityManager em;
    
    @Autowired
    public SpringConfig(EntityManager em){
        this.em=em;
    }
    
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    
    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        return new UserRepository(em);
    }
}
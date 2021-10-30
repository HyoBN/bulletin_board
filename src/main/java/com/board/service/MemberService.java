package com.board.service;

import java.util.List;
import java.util.Optional;

import com.board.entity.Member;
import com.board.repository.MemberRepository;

import org.springframework.transaction.annotation.Transactional;


@Transactional
public class MemberService{
    private final MemberRepository memberRepository;
    
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    
    public Long join(Member member){
            try{
            memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
            } catch (Exception IllegalStateException){
                return 0L; 
            }

        memberRepository.save(member);
        return member.getId();
    }
    
    public List<Member> findMembers() {
         return memberRepository.findAll();
    }
    
    
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
        
    }
}
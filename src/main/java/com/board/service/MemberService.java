package com.board.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.board.entity.Member;
import com.board.repository.MemberRepository;


@Transactional
@Service
public class MemberService{
    private final MemberRepository memberRepository;
    
    @Autowired
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
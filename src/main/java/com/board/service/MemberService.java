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
    
    public String join(Member member){
        try{
        memberRepository.findById(member.getId())
            .ifPresent(m -> {
                throw new IllegalStateException("ID 중복");
            });
        } catch (Exception IllegalStateException){
            return "idOverlap"; 
        }
        try{
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("회원명 중복");
            });
        } catch (Exception IllegalStateException){
            return "nameOverlap"; 
        }

        memberRepository.save(member);
        return "loginSuccess";
    }
    
    public List<Member> findMembers() {
         return memberRepository.findAll();
    }
    
    public Optional<Member> findOne(String memberId){
        return memberRepository.findById(memberId);
    }
    
    public Long isMember(Member member){    
        try{
            memberRepository.findByIdAndPassword(member.getId(), member.getPassword())
                .ifPresent(m -> {
                    throw new IllegalStateException("Member Checked!");
                });
            } catch (Exception IllegalStateException){
                return 0L; 
            }        
        return 1L;
    }
}
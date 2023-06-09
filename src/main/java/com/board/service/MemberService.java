package com.board.service;

import com.board.entity.Member;
import com.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService{
    private final MemberRepository memberRepository;
    
    public String join(Member member){
        try{
        memberRepository.findByUserId(member.getUserId())
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

    public String findName(String memberId){
        Optional<Member> member = memberRepository.findByUserId(memberId);
        return member.get().getName();
    }
    
    public boolean isMember(Member member){
        try {
            memberRepository.findByUserIdAndPassword(member.getUserId(), member.getPassword()).get();
        }catch (Exception e){
                return false;
            }        
        return true;
    }
}
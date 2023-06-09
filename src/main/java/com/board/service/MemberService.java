package com.board.service;

import com.board.controller.MemberForm;
import com.board.entity.JoinResult;
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
    
    public JoinResult join(Member member){

        if (memberRepository.existsByUserId(member.getUserId())) {
            return JoinResult.ID_OVERLAP;
        } else if (memberRepository.existsByName(member.getName())) {
            return JoinResult.NAME_OVERLAP;
        }else{
            return JoinResult.JOIN_SUCCESS;
        }
    }
    
    public List<Member> findMembers() {
         return memberRepository.findAll();
    }

    public Member checkMember(MemberForm form) {
        Optional<Member> optionalMember = memberRepository.findByUserIdAndPassword(form.getUserId(), form.getPassword());
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            return member;
        }else{
            return null;
        }
    }
}
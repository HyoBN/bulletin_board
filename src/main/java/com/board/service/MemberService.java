package com.board.service;

import com.board.dto.MemberRequestDto;
import com.board.dto.MemberResponseDto;
import com.board.entity.JoinResult;
import com.board.entity.Member;
import com.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    
    public List<MemberResponseDto> findMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtos = members.stream()
                .map(m -> new MemberResponseDto(m))
                .collect(Collectors.toList());
        return memberResponseDtos;
    }

    public Member checkMember(MemberRequestDto form) {
        Optional<Member> optionalMember = memberRepository.findByUserIdAndPassword(form.getUserId(), form.getPassword());
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            return member;
        }else{
            return null;
        }
    }
}
package com.board.dto;

import com.board.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class MemberResponseDto {
    @NotNull
    private String userId;
    @NotNull
    private String name;

    public MemberResponseDto(Member member) {
        this.userId = member.getUserId();
        this.name = member.getName();
    }
}
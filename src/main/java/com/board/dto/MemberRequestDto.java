package com.board.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class MemberRequestDto {
    private String userId;
    @NotNull
    private String name;
    @NotNull
    private String password;
}
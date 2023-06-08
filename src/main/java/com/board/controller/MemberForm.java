package com.board.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class MemberForm{
    private String userId;
    @NotNull
    private String name;
    @NotNull
    private String password;
}
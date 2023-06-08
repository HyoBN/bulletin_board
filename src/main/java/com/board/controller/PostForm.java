package com.board.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class PostForm{
    @NotNull
    private String title;
    private String writer;
    private String contents;
    private String date;
}
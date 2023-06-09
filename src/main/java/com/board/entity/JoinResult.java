package com.board.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JoinResult {
    JOIN_SUCCESS("로그인 성공"),
    ID_OVERLAP("이미 존재하는 ID입니다."),
    NAME_OVERLAP("이미 존재하는 이름입니다.");

    private final String message;
}

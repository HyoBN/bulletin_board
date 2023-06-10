package com.board.entity;

import com.board.dto.MemberRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String name;
    private String password;

    @Builder
    public Member(MemberRequestDto form) {
        this.userId = form.getUserId();
        this.name = form.getName();
        this.password = form.getPassword();
    }
}
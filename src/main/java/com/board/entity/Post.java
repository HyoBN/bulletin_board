package com.board.entity;

import com.board.dto.PostRequestDto;
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
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String writer;
    private String contents;
    private String date;

    @Builder
    public Post(PostRequestDto form) {
        this.title = form.getTitle();
        this.writer = form.getWriter();
        this.contents = form.getContents();
    }
}
package com.board.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity 
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Board extends TimeEntity {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(length = 10, nullable = false)
    private String writer;
    
    @Column(length = 100, nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    
    @Builder 
    public Board(Long id, String title, String content, String writer){
        this.id=id;
        this.title=title;
        this.content=content;
        this.writer=writer;
        
    }
}

package com.board.dto;

import java.time.LocalDateTime;

import com.board.domain.entity.Board;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@ToString 
@NoArgsConstructor
@Repository
public class BoardDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate; 
    
    public Board toEntity(){
        Board build = Board.builder()
            .id(id)
            .writer(writer)
            .title(title)
            .content(content)
            .build();
        return build;
    }
    
    @Builder
    public BoardDto(Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDateTime){
        this.id = id;
        this.writer=writer;
        this.title = title;
        this.content=content;
        this.createdDate=createdDate;
        this.modifiedDate=modifiedDate;
    }
}

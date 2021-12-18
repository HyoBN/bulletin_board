package com.board.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    private String writer;
    
    private String contents;
    
    private String date;
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id) {
        this.id=id;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title=title;
    }
    
    public String getWriter(){
        return writer;
    }
    
    public void setWriter(String writer){
        this.writer=writer;
    }
    
    public String getContents(){
        return contents;
    }
    
    public void setContents(String contents){
        this.contents=contents;
    }
    
    public String getDate(){
        return date;
    }
}

package com.board.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column

@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDNTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String writer;
    
    @Column
    private String contents;
    
    @Column(nullable = false)
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
    
    public void setContenst(String contents){
        this.contents=contents;
    }
    
    public String getDate(){
        return date;
    }
    
    public void setDate(String date){
        this.date=date;
    }
}

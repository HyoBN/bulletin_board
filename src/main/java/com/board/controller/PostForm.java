
package com.board.controller;

import javax.validation.constraints.*;

import org.springframework.validation.*;

public class PostForm{
    
    @NotNull
    private String title;
    
    private String writer;
    
    private String contents;
    
    private String date;
    
    public String getTitle(){
        return title;
    }
    
    public String getWriter(){
        return writer;
    }
    
    public String getDate(){
        return date;
    }
    
    public String getContents(){
        return contents;
    }
    
    public void setTitle(String title){
        this.title=title;
    }
    
    public void setWriter(String writer){
        this.writer=writer;
    }
        
    public void setContents(String contents){
        this.contents=contents;
    }
}
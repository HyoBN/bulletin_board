package com.board.controller;
//import javax.validation.constraints.NotBlank;
import javax.validation.constraints.*;

import org.springframework.validation.*;

public class MemberForm{
    
    private String id;
    
    @NotNull
    private String name;
    
    @NotNull
    private String password;
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
}
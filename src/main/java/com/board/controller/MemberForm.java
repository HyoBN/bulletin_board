package com.board.controller;

import javax.validation.constraints.NotNull;

public class MemberForm{
    
    private String id;
    
    @NotNull
    private String name;
    
    @NotNull
    private String password;
    
    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public String getPassword(){
        return password;
    }   
    public void setPassword(String password){
        this.password = password;
    }
}
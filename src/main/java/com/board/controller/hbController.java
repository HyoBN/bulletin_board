package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class hbController{
    @GetMapping("/")
    public String list(){
        return "board/list";
    }
    
    @GetMapping("/post")
    public String write(){
        return "board/write";
    }
}

/*
hbcontroller
package com.board.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class hbController {
    
    @GetMapping("/")
    public String home(){
        return "home";
    }
    
    @GetMapping("members/sign_in")
    public String sign_in(){
        return "members/sign_in";
    }
    
    @GetMapping("members/sign_up")
    public String sign_up(){
        return "members/sign_up";
    }
}*/
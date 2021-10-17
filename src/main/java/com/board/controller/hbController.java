package com.board.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class hbController {
    
    @GetMapping("/")
    public String home(){
        return "home";
    }
    
}

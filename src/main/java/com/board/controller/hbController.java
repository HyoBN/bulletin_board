package com.board.controller;

import com.board.dto.BoardDto;
import com.board.service.BoardService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class hbController{
    
    private BoardService boardService;
    
    public hbController(BoardService boardService) {
        this.boardService=boardService;
    }
    
    @GetMapping("/")
    public String list(){
        return "board/list";
    }
    
    @GetMapping("/post")
    public String write(){
        return "board/write";
    }
    
    @PostMapping("/post")
    public String writee(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
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
package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.board.entity.Member;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController{
    
    //비회원의 home과 회원의 home을 분리하여 연결.
    @GetMapping("/")
    public String home(@SessionAttribute(name = "loginMember", required = false) Member loginMember, Model model){
        
        // 세션에 회원 데이터가 없으면 홈으로 이동
        if (loginMember == null) {
            return "home";
        }

        // 세션이 유지되면 로그인 홈으로 이동
        model.addAttribute("loginMessage", loginMember.getName()+"님 로그인 상태입니다."); 
        return "loginHome";
    }
    
}
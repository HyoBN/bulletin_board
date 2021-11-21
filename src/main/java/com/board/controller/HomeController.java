package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.board.entity.Member;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
//import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController{
    
    //비회원의 home과 회원의 home을 분리하여 연결시킴.
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){
        
        HttpSession session = request.getSession(false);
        
        if(session==null){
            return "home";
        }
        
        Member loginMember = (Member) session.getAttribute("loginMember");
        
        
        
        // 세션에 회원 데이터가 없으면 홈으로 이동
        if (loginMember != null) {
                // 세션이 유지되면 로그인 홈으로 이동
            model.addAttribute("loginMessage", /*loginMember.getName()+*/"님 로그인 상태입니다."); 
            return "loginHome";

        }
        return "home";
        
    }
}
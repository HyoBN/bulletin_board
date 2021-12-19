package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import com.board.entity.Member;
import com.board.service.PostService;


@Controller
public class HomeController{
    
    private PostService postservice;
    
    @Autowired
    public HomeController(PostService postservice){
        this.postservice=postservice;
    }
    
    //비회원의 home과 회원의 home을 분리하여 연결시킴.
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){
        
        HttpSession session = request.getSession(false);
        
        // 세션에 회원 데이터가 없으면 홈으로 이동
        if(session==null){
            return "home";
        }
        
        // 세션이 유지되면 로그인 홈으로 이동
        Member loginMember = (Member) session.getAttribute("loginMember");
        //System.out.println("로그인 멤버 name : ");
        //System.out.println(loginMember.getName());
        
        if (loginMember != null) {
            model.addAttribute("loginMessage", loginMember.getName()+"님 로그인 상태입니다."); 
            model.addAttribute("posts",postservice.findPosts());
            return "boardHome";

        }
        return "home";
        
    }
}
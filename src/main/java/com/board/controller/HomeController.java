package com.board.controller;

import com.board.dto.MemberResponseDto;
import com.board.entity.Member;
import com.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final PostService postservice;

    //비회원의 home과 회원의 home을 분리하여 연결시킴.
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        MemberResponseDto loginMember = (MemberResponseDto) session.getAttribute("loginMember");

        if (session == null || loginMember == null) {
            return "home";
        }
        model.addAttribute("loginMessage", loginMember.getName() + "님 로그인 상태입니다.");
        model.addAttribute("posts", postservice.findPosts());
        return "boardHome";
    }
    @GetMapping("/sessionFail")
    public String sessionFail(){
        return "sessionFail";
    }
}
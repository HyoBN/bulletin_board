package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.validation.*;
import com.board.entity.Member;
import com.board.repository.MemberRepository;
import com.board.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MemberController {
    
    private MemberService memberService;
    
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    
    @PostMapping("/members/new")
    public String create(MemberForm form, Model msg){
        Member member = new Member();
        member.setId(form.getId());
        member.setName(form.getName());
        member.setPassword(form.getPassword());
       
        String joinMessage=memberService.join(member);
        if(joinMessage=="idOverlap"){ 
            msg.addAttribute("loginMessage", "이미 존재하는 ID입니다"); 
            return "members/createMemberForm";
        }
        else if(joinMessage=="nameOverlap"){ 
            msg.addAttribute("loginMessage", "이미 존재하는 이름입니다"); 
            return "members/createMemberForm";
        }
        return "redirect:/";
    }
    
    @PostMapping("/signIn")
    public String signIn(MemberForm form, Model msg, HttpServletRequest request){
        Member member = new Member();
        member.setId(form.getId());
        member.setPassword(form.getPassword());
        
        if(memberService.isMember(member) == 0L){
            member.setName(memberService.findName(form.getId()));
            HttpSession session = request.getSession();
            session.setAttribute("loginMember",member);
            msg.addAttribute("loginMessage", member.getId()+"님 환영합니다!!"); 
            return "redirect:/";
        }
        return "loginFail";
    }
    
    @PostMapping("/logout")
    public String Logout(HttpServletRequest request){
        
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        
        return "redirect:/";
    }
    
    @GetMapping("/members")
    public String list(Model model){
        model.addAttribute("members",memberService.findMembers());
        return "members/memberList";
    }
}
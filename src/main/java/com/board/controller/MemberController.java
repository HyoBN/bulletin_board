package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import com.board.entity.Member;
import com.board.repository.MemberRepository;
import com.board.service.MemberService;


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
        member.setName(form.getName());
        member.setPassword(form.getPassword());
       
        if(memberService.join(member)==0L){ 
            msg.addAttribute("loginMessage", "이미 존재하는 회원입니다!"); 
            return "members/createMemberForm";
        }
        return "redirect:/";
    }
     
    @PostMapping("/signIn")
    public String signIn(MemberForm form, Model msg){
        Member member = new Member();
        
        member.setPassword(form.getPassword());
        member.setName(form.getName());
        //위 두 문장 순서만 바꾸니까 제대로 실행됨. Name을 먼저하면 form.name에는 null이 저장됨(password는 정상적으로 저장됨.)

        if(memberService.isMember(member) == 0L){
            msg.addAttribute("loginMessage", member.getName()+"님 환영합니다!!"); 
            return "loginOK";
        }
        return "loginFail";
    }
    
    @GetMapping("/members")
    public String list(Model model){
        model.addAttribute("members",memberService.findMembers());
        return "members/memberList";
    }
}
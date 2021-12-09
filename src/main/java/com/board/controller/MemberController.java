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
        member.setName(form.getName());
        member.setPassword(form.getPassword());
       
        if(memberService.join(member)==0L){ 
            msg.addAttribute("loginMessage", "이미 존재하는 회원입니다!"); 
            return "members/createMemberForm";
        }
        return "redirect:/";
    }
    
    @PostMapping("/signIn")
    public String signIn(MemberForm form, Model msg, HttpServletRequest request){
        Member member = new Member();
        
        member.setPassword(form.getPassword());
        member.setName(form.getName());
        //위 두 문장 순서만 바꾸니까 제대로 실행됨. Name을 먼저하면 form.name에는 null이 저장됨(password는 정상적으로 저장됨.)

        if(memberService.isMember(member) == 0L){
            HttpSession session = request.getSession();
            session.setAttribute("loginMember",member);
            
            //여기에 post를 속성으로 주던가 해야하는데 그러면 member,post controller 혼용하는 꼴..
            msg.addAttribute("loginMessage", member.getName()+"님 환영합니다!!"); 
            return "redirect:/";
        }
        
        //loginFail 페이지가 아닌 팝업창, 메시지 띄우는 것으로 바꾸기.
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
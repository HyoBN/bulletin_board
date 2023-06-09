package com.board.controller;

import com.board.entity.Member;
import com.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    
    @PostMapping("/members/new")
    public String create(MemberForm form, Model msg, Model model){
        Member member = new Member(form);
       
        String joinMessage=memberService.join(member);
        if(joinMessage=="idOverlap"){ 
            msg.addAttribute("loginMessage", "이미 존재하는 ID입니다");
            model.addAttribute("memberFormData",member);
            return "members/createMemberForm";
        }
        else if(joinMessage=="nameOverlap"){ 
            msg.addAttribute("loginMessage", "이미 존재하는 이름입니다");
            model.addAttribute("memberFormData",member);
            return "members/createMemberForm";
        }
        return "redirect:/";
    }
    
    @PostMapping("/signIn")
    public String signIn(MemberForm form, HttpServletRequest request){
        Member member = new Member(form);
        if(memberService.isMember(member)){
            member.setName(memberService.findName(form.getUserId()));
            HttpSession session = request.getSession();
            session.setAttribute("loginMember",member);
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
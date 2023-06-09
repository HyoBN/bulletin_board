package com.board.controller;

import com.board.entity.JoinResult;
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
    public String create(MemberForm form, Model model){
        Member member = new Member(form);
        JoinResult joinResult = memberService.join(member);

        if (joinResult.equals(JoinResult.JOIN_SUCCESS)) {
            return "redirect:/";
        }else{
            model.addAttribute("joinMessage", joinResult.getMessage());
            model.addAttribute("memberFormData", member);
            return "members/createMemberForm";
        }
    }
    
    @PostMapping("/signIn")
    public String signIn(MemberForm form, HttpServletRequest request){
        Member member = memberService.checkMember(form);
        if (member == null) {
            return "loginFail";
        }else{
            HttpSession session = request.getSession();
            session.setAttribute("loginMember",member);
            return "redirect:/";
        }
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
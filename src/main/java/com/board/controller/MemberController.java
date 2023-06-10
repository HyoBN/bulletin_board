package com.board.controller;

import com.board.dto.MemberRequestDto;
import com.board.dto.MemberResponseDto;
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
    public String create(MemberRequestDto form, Model model){
        Member requestMember = new Member(form);
        JoinResult joinResult = memberService.join(requestMember);

        if (joinResult.equals(JoinResult.JOIN_SUCCESS)) {
            return "redirect:/";
        }else{
            model.addAttribute("joinMessage", joinResult.getMessage());
            model.addAttribute("memberFormData", requestMember);
            return "members/createMemberForm";
        }
    }
    
    @PostMapping("/signIn")
    public String signIn(MemberRequestDto form, HttpServletRequest request){
        Member member = memberService.checkMember(form);
        if (member == null) {
            return "loginFail";
        }else{
            HttpSession session = request.getSession();
            MemberResponseDto memberResponseDto = new MemberResponseDto(member);
            session.setAttribute("loginMember",memberResponseDto);
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
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
    
    private final MemberService memberService;
    private final MemberRepository member;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    
    public MemberController(MemberRepository member){
        this.member = member;
    }
    
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    
    @PostMapping("/members/new")
    public String create(MemberForm form, Model msg){
        Member member = new Member();
        member.setName(form.getName());

        if(memberService.join(member)==0L){
            msg.addAttribute("loginMessage", "이미 존재하는 회원입니다!");
            return "members/createMemberForm";
        }
        return "redirect:/";
    }
    
    @PostMapping("/signIn")
    public String signIn(String inputName, String inputPassword){
        //log.info("id : {}, pw : {}", inputName, inputPassword);
        Optional<Member> member = this.member.findByNameAndPassword(inputName, inputPassword);
        if(member != null){
            return "loginOK";
        }
        return "loginFail";
    }
    
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members=memberService.findMembers();
        model.addAttribute("members",members);            
        return "members/memberList";
    }
}
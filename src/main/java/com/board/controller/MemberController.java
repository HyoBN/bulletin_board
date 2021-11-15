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
    private MemberRepository member;
    
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    
   /* @Autowired
    public MemberController(MemberRepository member){
        this.member = member;
    }*/
    
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    
    @PostMapping("/members/new")
    public String create(MemberForm form, Model msg){
        Member member = new Member();
        //Optional<String> name = Optional.ofNullable(form.getName());
        member.setName(form.getName());
        member.setPassword(form.getPassword());
            
        System.out.println("***********************로그 출력********************");
        System.out.println("form에 저장된 이름과 비밀번호 : "+form.getName()+form.getPassword());
        
        //System.out.println("member에 저장된 이름과 비밀번호 : "+member.getName()+member.getPassword());
        
            
        if(memberService.join(member)==0L){ 
            msg.addAttribute("loginMessage", "이미 존재하는 회원입니다!"); 
            return "members/createMemberForm";
        }
        return "redirect:/";
    }
     
    @PostMapping("/signIn")
    public String signIn(String inputName, String inputPassword){
        //Optional<Member> member = Optional.ofNullable(this.member.findByNameAndPassword(inputName, inputPassword));
        if(memberService.isMember(inputName, inputPassword) != null){
            return "loginOK";
        }
        return "loginFail";
    }
    
    @GetMapping("/members")
    public String list(Model model){
        //Optional<List<Member>> members=Optional.ofNullable(memberService.findMembers());
        model.addAttribute("members",memberService.findMembers());
        return "members/memberList";
    }
}
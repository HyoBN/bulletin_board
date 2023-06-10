package com.board.controller;

import com.board.dto.MemberResponseDto;
import com.board.dto.PostRequestDto;
import com.board.entity.Member;
import com.board.entity.Post;
import com.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postservice;
    
    @GetMapping("/posts/new")
    public String createForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        MemberResponseDto loginMember = (MemberResponseDto) session.getAttribute("loginMember");
        model.addAttribute("loginMemberName",loginMember.getName());
        return "posts/createPostForm";
    }
    
    @PostMapping("/posts/new")
        public String create(PostRequestDto form){
        Post post = new Post(form);
        postservice.upload(post);
        return "redirect:/";
    }
    
    @GetMapping("/posts/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        Post post = postservice.findOne(id);
        model.addAttribute("post",post);
        return "posts/postDetail";
    }

    @GetMapping("/posts/modify/{id}")
    public String toModifyPage(@PathVariable("id") Long id,Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Post post = postservice.findOne(id);
        MemberResponseDto loginMember = (MemberResponseDto) session.getAttribute("loginMember");
        model.addAttribute("post",post);

        if (postservice.writerCheck(post, loginMember)) {
            return "posts/postModify";
        }else{
            model.addAttribute("sessionMessage","작성자만 수정가능합니다.");
            return "posts/postDetail";
        }
    }
    
    @PostMapping("/posts/modify/{id}")
    public String updatePost(@PathVariable("id") Long id, PostRequestDto form, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Post post = postservice.findOne(id);
        MemberResponseDto loginMember = (MemberResponseDto) session.getAttribute("loginMember");

        if (postservice.writerCheck(post, loginMember)) {
            postservice.modifyPost(id, form);
        }
        return "redirect:/";
    }
    
    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable("id") Long id,Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Post post = postservice.findOne(id);
        MemberResponseDto loginMember = (MemberResponseDto) session.getAttribute("loginMember");
        if (postservice.writerCheck(post, loginMember)) {
            postservice.delete(id);
            return "redirect:/";
        }else{
            model.addAttribute("post",post);
            model.addAttribute("sessionMessage","작성자만 삭제가능합니다.");
            return "posts/postDetail";
        }
    }
}
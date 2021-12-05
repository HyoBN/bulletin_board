package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PostController {
    private PostService postservice;
    
    @Autowired
    public PostController(PostService postservice){
        this.postservice=postservice;
    }
    
    @GetMapping("/posts/new")
    public String createForm() {
        return "posts/createPostForm";
    }
    
    @PostMapping("/posts/new")
    public String create(PostForm form, Model msg){
        
        // 세션 만료되었을 경우(로그아웃 되었을 경우) 예외처리하기.
        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setWriter(form.getWriter());
        post.setDate(form.getDate());
        
        return "redirect:/";
    }
    
    @PostMapping("/logout")
    public String Logout(HttpServletRequest request){
        
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        
        return "redirect:/";
    }
    
    @GetMapping("/posts")
    public String list(Model model){
        model.addAttribute("posts",postservice.findPosts());
        return "posts/postList";
    }
}
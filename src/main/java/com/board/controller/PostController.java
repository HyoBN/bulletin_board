package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.board.entity.Post;
import com.board.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PostController {
    private PostService postservice;
    
    @Autowired
    public PostController(PostService postservice){
        this.postservice=postservice;
    }
    
    @GetMapping("/posts/new")
    public String createForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session==null){
            // 세션 만료되었다는 팝업 띄우기.
            model.addAttribute("sessionMessage","로그인 후 접근 가능합니다.");
            return "sessionFail";
        }
        return "posts/createPostForm";
    }
    
    @PostMapping("/posts/new")
        public String create(PostForm form, Model model, HttpServletRequest request){        
        HttpSession session = request.getSession(false);
        if(session==null){
            // 세션 만료되었다는 팝업 띄우기.
             model.addAttribute("sessionMessage","로그인 후 접근 가능합니다.");
        }
            
        else if(session!=null){
            Post post = new Post();
            post.setTitle(form.getTitle());
            post.setWriter(form.getWriter());
            post.setContents(form.getContents());
            post.setDate(form.getDate());
            postservice.upload(post);
            
        }
        return "redirect:/";
    }
    
    @GetMapping("/posts/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null){
            // 세션 만료되었다는 팝업 띄우기.
             model.addAttribute("sessionMessage","로그인 후 접근 가능합니다.");
        }
        Post post = postservice.findOne(id);
        model.addAttribute("post",post);
        return "posts/postDetail";
    }
    
    @GetMapping("/posts/modify/{id}")
    public String toModifyPage(@PathVariable("id") Long id,Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null){
            // 세션 만료되었다는 팝업 띄우기.
            model.addAttribute("sessionMessage","로그인 후 접근 가능합니다.");
            return "sessionFail";
        }
        model.addAttribute("post",postservice.findOne(id));
        return "posts/postModify";
    }
    
    @PostMapping("/posts/modify/{id}")
    public String updatePost(@PathVariable("id") Long id,Model model, PostForm form, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null){
            // 세션 만료되었다는 팝업 띄우기.
            model.addAttribute("sessionMessage","로그인 후 접근 가능합니다.");
            return "sessionFail";
        }
        else if(session!=null){
            Post post = postservice.findOne(id);
            post.setTitle(form.getTitle());
            post.setWriter(form.getWriter());
            post.setContents(form.getContents());
            post.setDate(form.getDate());
            postservice.upload(post);
            
        }
        return "redirect:/";
    }
}
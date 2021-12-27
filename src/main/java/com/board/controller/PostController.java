
package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.ui.Model;

import com.board.entity.Post;
import com.board.service.PostService;
import com.board.entity.Member;

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
            model.addAttribute("sessionMessage","로그인 후 접근 가능합니다.");
            return "sessionFail";
        }
        Member member = (Member) session.getAttribute("loginMember");
        model.addAttribute("loginMemberName",member.getName());
        return "posts/createPostForm";
    }
    
    @PostMapping("/posts/new")
        public String create(PostForm form, Model model, HttpServletRequest request){        
        HttpSession session = request.getSession(false);
        if(session==null){
             model.addAttribute("sessionMessage","로그인 후 접근 가능합니다.");
        }
            
        else if(session!=null){
            Post post = new Post();
            post.setTitle(form.getTitle());
            post.setWriter(form.getWriter());
            post.setContents(form.getContents());
            postservice.upload(post);
        }
        return "redirect:/";
    }
    
    @GetMapping("/posts/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null){          
            return "sessionFail";
        }
        Post post = postservice.findOne(id);
        model.addAttribute("post",post);
        return "posts/postDetail";
    }
    
    @GetMapping("/posts/modify/{id}")
    public String toModifyPage(@PathVariable("id") Long id,Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null){
            return "sessionFail";
        }
        
        Post post = postservice.findOne(id);
        Member loginMember = (Member) session.getAttribute("loginMember");
                
        if(post.getWriter().equals(loginMember.getName())==false){
            model.addAttribute("post",post);
            model.addAttribute("sessionMessage","작성자만 수정가능합니다.");
            return "posts/postDetail";
        }
        else{
            model.addAttribute("post",post);
            return "posts/postModify";
        }
    }
    
    @PostMapping("/posts/modify/{id}")
    public String updatePost(@PathVariable("id") Long id, PostForm form, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null){
            return "sessionFail";
        }
        else if(session!=null){
            Post post = postservice.findOne(id);
            post.setTitle(form.getTitle());
            post.setWriter(form.getWriter());
            post.setContents(form.getContents());
            postservice.upload(post);
        }
        return "redirect:/";
    }
    
    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable("id") Long id,Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null){
            return "sessionFail";
        }
        Post post = postservice.findOne(id);
        Member loginMember = (Member) session.getAttribute("loginMember");
        if(post.getWriter().equals(loginMember.getName())==false){
            model.addAttribute("post",post);
            model.addAttribute("sessionMessage","작성자만 삭제가능합니다.");
            return "posts/postDetail";
        }
        else{
            postservice.delete(id);
            return "redirect:/";
        }
    }
}
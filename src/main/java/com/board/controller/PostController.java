package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String createForm() {
        return "posts/createPostForm";
    }
    
    @PostMapping("/posts/new")
        public String create(PostForm form, Model model, HttpServletRequest request){
        
        HttpSession session = request.getSession(false);
        /*if(session==null){
            // 세션 만료되었다는 팝업 띄우기.
             return "home";
            
            return "members/createMemberForm";
        }*/
        if(session!=null){
            Post post = new Post();
            post.setTitle(form.getTitle());
            post.setWriter(form.getWriter());
            post.setContents(form.getContents());
            post.setDate(form.getDate());
            postservice.upload(post);
            model.addAttribute("posts",postservice.findPosts());
        }
        return "redirect:/";
       
    }
    
    @GetMapping("/posts")
    public String list(Model model){
        model.addAttribute("posts",postservice.findPosts());
        return "boardHome";
    }
}
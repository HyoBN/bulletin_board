package com.board.service;

import com.board.controller.PostForm;
import com.board.entity.Member;
import com.board.repository.PostRepository;
import com.board.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PostService{
    private final PostRepository postRepository;
    
    public void upload(Post post){
        postRepository.save(post);
    }

    public void modifyPost(Long id, PostForm form) {
        Post post = findOne(id);
        post.setTitle(form.getTitle());
        post.setWriter(form.getWriter());
        post.setContents(form.getContents());
        upload(post);
    }

    public boolean writerCheck(Post post, Member member) {
        if (post.getWriter().equals(member.getName())) {
            return true;
        } else{
            return false;
        }
    }

    
    public void delete(Long id){
        postRepository.deleteById(id);
    }
    
    public List<Post> findPosts(){
        return postRepository.findAllByOrderByIdDesc();
    }
    
    public Post findOne(Long postId){
        return postRepository.findById(postId).get();
    }
}
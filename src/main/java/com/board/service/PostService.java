package com.board.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.board.entity.Post;
import com.board.repository.PostRepository;

@Transactional
@Service
public class PostService{
    private final PostRepository postRepository;
    
    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    
    public Long upload(Post post){
        postRepository.save(post);
        return post.getId();
    }
    
    public List<Post> findPosts(){
        return postRepository.findAll();
    }
    
    public Post findOne(Long postId){
        Post post = new Post();
        post=postRepository.findById(postId);
        return postRepository.findById(postId);
    }
    
}
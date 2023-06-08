package com.board.service;

import com.board.repository.PostRepository;
import com.board.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    
    public void delete(Long id){
        postRepository.deleteById(id);
    }
    
    public List<Post> findPosts(){
        return postRepository.findAllByOrderByIdDesc();
    }
    
    public Post findOne(Long postId){
        return postRepository.findById(postId);
    }
}
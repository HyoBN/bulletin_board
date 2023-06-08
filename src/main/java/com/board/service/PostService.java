package com.board.service;

import com.board.repository.PostRepository;
import com.board.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
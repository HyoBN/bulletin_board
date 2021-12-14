package com.board.repository;

import com.board.entity.Post;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
    
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post save(Post post);
    Post findById(Long id);
    //Optional<Post> findById(Long id);
    Optional<Post> findByTitle(String title);
    Optional<Post> findByDate(String date);
    Optional<Post> findByWriter(String writer);
    List<Post> findAll();

}

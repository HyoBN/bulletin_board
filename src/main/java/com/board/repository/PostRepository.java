package com.board.repository;

import com.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post save(Post post);
    Post findById(Long id);
    void deleteById(Long id);
    Optional<Post> findByTitle(String title);
    Optional<Post> findByDate(String date);
    List<Post> findAllByOrderByIdDesc();
}

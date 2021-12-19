package com.board.repository;

import com.board.entity.Member;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
    
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member save(Member member);
    Optional<Member> findById(String id);
    Optional<Member> findByName(String name);
    Optional<Member> findByIdAndPassword(String id, String password);
    List<Member> findAll();

}

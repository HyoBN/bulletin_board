package com.board.repository;

import com.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
    
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(String id);
    Optional<Member> findByName(String name);
    Optional<Member> findByIdAndPassword(String id, String password);
    }
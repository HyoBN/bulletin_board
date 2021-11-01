package com.board.repository;

import com.board.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    Optional<Member> findByNameAndPassword(String name, String password);
    List<Member> findAll();

}

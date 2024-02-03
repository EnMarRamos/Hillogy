package com.hillogy.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hillogy.library.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

	Optional<Member> findByDni(String dni);

	Optional<Member> findByCardId(int cardId);
}

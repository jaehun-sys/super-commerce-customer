package com.bestcommerce.member.repository;

import com.bestcommerce.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberEmail(String email);

    Boolean existsByMemberEmail(String email);
}

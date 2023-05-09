package com.bestcommerce.member.service;

import com.bestcommerce.member.entity.Member;
import com.bestcommerce.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(MemberDetailService.class);

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername :: {}", username);
        return memberRepository.findByMemberEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(Member member) {
        return Member.builder()
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .roles(member.getRoles())
                .build();
    }

}

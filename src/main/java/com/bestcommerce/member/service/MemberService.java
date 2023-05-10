package com.bestcommerce.member.service;

import com.bestcommerce.jwt.JwtTokenProvider;
import com.bestcommerce.jwt.TokenInfo;
import com.bestcommerce.member.entity.Member;
import com.bestcommerce.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private static final Logger log = LoggerFactory.getLogger(MemberService.class);

    private final MemberRepository memberRepository;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenInfo login(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        try {
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
            log.info("authentication getAuthorities: {}", authentication.getAuthorities());
            return tokenInfo;
        }catch (RuntimeException e){
            e.printStackTrace();
            return TokenInfo.builder().message(e.getMessage()).build();
        }
    }

    public Long saveMember(String email, String password){
        if(!memberRepository.existsByMemberEmail(email)){
            return memberRepository.save(Member.builder()
                    .memberEmail(email)
                    .memberPassword(passwordEncoder.encode(password))
                    .build()).getId();
        }
        throw new RuntimeException("중복된 이메일 입니다.");
    }

    public Member findMember(String email){
        return memberRepository.findByMemberEmail(email).orElseThrow(()-> new RuntimeException("등록된 사용자가 아닙니다."));
    }
}

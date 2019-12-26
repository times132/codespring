package com.codespring.ch03.security;

import com.codespring.ch03.security.domain.CustomUser;
import com.codespring.ch03.domain.MemberVO;
import com.codespring.ch03.mapper.MemberMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {

    @Setter(onMethod_ = {@Autowired})
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.warn("Load User By UserName: " + username);

        MemberVO vo = memberMapper.read(username);

        log.warn("queried by member mapper: " + vo);

        return vo == null ? null : new CustomUser(vo);
    }
}

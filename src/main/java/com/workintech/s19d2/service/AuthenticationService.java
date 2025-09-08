package com.workintech.s19d2.service;

import com.workintech.s19d2.entity.Member;
import com.workintech.s19d2.entity.Role;
import com.workintech.s19d2.repository.MemberRepository;
import com.workintech.s19d2.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    public static final String ROLE_USER  = "USER";

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member register(String email, String password) {
        String normalized = email.trim().toLowerCase();
        if (memberRepository.existsByEmail(normalized)) {
            throw new RuntimeException("User with given email already exists! Email: " + normalized);
        }

        Set<Role> roles = new LinkedHashSet<>();
        roles.add(roleRepository.findByAuthority(ROLE_USER)
                .orElseGet(() -> {
                    Role r = new Role();
                    r.setAuthority(ROLE_USER);
                    return roleRepository.save(r);
                }));

        Member m = new Member();
        m.setEmail(normalized);
        m.setPassword(passwordEncoder.encode(password));
        m.setRoles(roles.stream().toList());
        return memberRepository.save(m);
    }
}

package com.workintech.s19d2.service;

import com.workintech.s19d2.entity.Member;
import com.workintech.s19d2.entity.Role;
import com.workintech.s19d2.repository.MemberRepository;
import com.workintech.s19d2.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

public class AuthenticationService {

    private final MemberRepository memberRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public AuthenticationService(MemberRepository memberRepo,
                                 RoleRepository roleRepo,
                                 PasswordEncoder encoder) {
        this.memberRepo = memberRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
    }

    public Member register(String email, String rawPassword) {
        memberRepo.findByEmail(email).ifPresent(m -> {
            throw new RuntimeException("User with given email already exist");
        });

        Role adminRole = roleRepo.findByAuthority("ADMIN")
                .orElseThrow(() -> new RuntimeException("Role not found: ADMIN"));

        String encoded = encoder.encode(rawPassword);

        Member member = new Member();
        member.setEmail(email);
        member.setPassword(encoded);
        member.setRoles(Collections.singletonList(adminRole));

        return memberRepo.save(member);
    }
}

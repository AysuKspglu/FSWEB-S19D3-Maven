package com.workintech.s19d2;

import com.workintech.s19d2.repository.RoleRepository;
import com.workintech.s19d2.entity.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

    @Bean
    CommandLineRunner seedRoles(RoleRepository roleRepo) {
        return args -> {
            roleRepo.findByAuthority("ROLE_USER").orElseGet(() -> roleRepo.save(Role.builder().authority("ROLE_USER").build()));
            roleRepo.findByAuthority("ROLE_ADMIN").orElseGet(() -> roleRepo.save(Role.builder().authority("ROLE_ADMIN").build()));
        };
    }
}

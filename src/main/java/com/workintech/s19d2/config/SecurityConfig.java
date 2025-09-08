package com.workintech.s19d2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/**").permitAll();
                    auth.requestMatchers("/welcome").permitAll();

                    // Account endpointleri rol bazlı kontrol
                    auth.requestMatchers(HttpMethod.GET, "/account/**")
                            .hasAnyAuthority("ADMIN", "USER");
                    auth.requestMatchers(HttpMethod.POST, "/account/**")
                            .hasAnyAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT, "/account/**")
                            .hasAnyAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE, "/account/**")
                            .hasAnyAuthority("ADMIN");

                    // diğer her şey login gerektirir
                    auth.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults()) // test için basic auth
                .formLogin(Customizer.withDefaults())
                .build();
    }
}

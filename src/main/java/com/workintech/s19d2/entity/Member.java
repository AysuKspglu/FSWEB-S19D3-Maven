package com.workintech.s19d2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "member", schema = "bank")
@Getter
@NoArgsConstructor
@Builder
public class Member implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "members_roles", schema = "bank",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    /* ------------ yardımcı setter'lar / kurucular ------------- */
    public void setRoles(Collection<Role> roles) {
        this.roles = (roles != null) ? new HashSet<>(roles) : new HashSet<>();
    }

    public Member(Long id, String email, String password, Set<Role> roles) {
        this.id = id; this.email = email; this.password = password;
        this.roles = (roles != null) ? new HashSet<>(roles) : new HashSet<>();
    }

    public Member(Long id, String email, String password, List<Role> roles) {
        this(id, email, password, roles != null ? new HashSet<>(roles) : new HashSet<>());
    }

    public Member(Long id, String email, String password, Collection<Role> roles) {
        this(id, email, password, roles != null ? new HashSet<>(roles) : new HashSet<>());
    }

    /* ----------------- UserDetails metotları ------------------- */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return roles; }

    @Override
    public String getUsername() { return email; }

    @Override
    public String getPassword() { return password; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    /* Setter’lar (email/password için) */
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setId(Long id) { this.id = id; }
}

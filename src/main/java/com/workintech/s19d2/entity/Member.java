package com.workintech.s19d2.entity;

import ch.qos.logback.core.util.COWArrayList;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor
@Entity
@Table(name= "member", schema="fsweb")

public class Member implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "email")
    private String email;

    @Column(name="password")
    private String password;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="member_role",schema="fsweb")
    joinColumns = {@JoinColumn(name="member_id")},
inverseJoinColumns = {@JoinColumn(name="role_id")})
private List<Role> = new ArrayList<>();


    @java.lang.Override
    public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @java.lang.Override
    public java.lang.String getPassword() {
        return null;
    }

    @java.lang.Override
    public String getUsername() {
        return email;
    }

    @java.lang.Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @java.lang.Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @java.lang.Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @java.lang.Override
    public boolean isEnabled() {
        return true;
    }
}

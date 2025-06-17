package com.example.demo.model;

import com.example.demo.dto.User;
import com.example.demo.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "app_user")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String uuid;

    @Enumerated(EnumType.STRING)
    @Getter
    private UserRole userRole;

    private boolean locked;

    private boolean enabled;

    public UserEntity() {}

    public UserEntity(User user) {
        this.uuid = user.uuid();
        this.locked = false;
        this.enabled = true;
        this.userRole = user.userRole();
        update(user);
    }

    public UserEntity update(User user){
        this.email = user.email();
        this.password = user.password().orElse(null);
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public User getDto(){
        return new User(uuid, email, userRole);
    }
}

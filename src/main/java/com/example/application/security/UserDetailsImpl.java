package com.example.application.security;

import com.example.application.data.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class that implements the methods related to UserDetails that is connected to Spring Security
 *
 */
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    UserEntity user;

    /**
     * implements spring security userdetails and connects it to our own entity model
     * @return the list  grantedAuthorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPasswordSalt();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

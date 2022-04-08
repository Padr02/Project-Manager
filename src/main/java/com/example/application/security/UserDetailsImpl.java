package com.example.application.security;

import com.example.application.data.RoleEnum;
import com.example.application.data.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Class that implements the methods related to UserDetails that is connected to Spring Security
 *
 */
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    UserEntity user;

    /**
     * Implement Spring Security userDetails and connect it to our own entity model
     * @return the list of grantedAuthorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<RoleEnum> roles = Set.of(user.getRole());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (RoleEnum role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
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

    @Bean
    public UserDetailsService userDetailsService(){

        return  new InMemoryUserDetailsManager();
    }
}

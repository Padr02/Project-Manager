package com.example.application.security;

import com.example.application.data.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    /**
     * Factory pattern that ensures the encryption of incoming password
     *
     * @return and forward the use of passwordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * Set up security rules for a Vaadin application and restrict all URLs except
     * for public resources and internal Vaadin URLs to authenticated user
     * @param http instance of the class HttpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    /**
     * Set up security rules for a Vaadin application and restrict all URLs except
     * for public resources and internal Vaadin URLs to authenticated user
     * @param web instance of the class WebSecurity
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**");
        super.configure(web);
    }

    /**
     * Method that is implemented in UserDetailsServiceImpl
     * @return userDetailsService
     */
    @Override
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }
}

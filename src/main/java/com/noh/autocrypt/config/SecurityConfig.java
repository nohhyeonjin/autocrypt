package com.noh.autocrypt.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsConfig corsConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.addFilter(corsConfig.corsFilter());
        http.formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password");
        http.authorizeRequests()
                .antMatchers("/posts").authenticated()
                .antMatchers("/post/**").authenticated()
                .anyRequest().permitAll();
    }

}

package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.Customizer;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//@Configuration
public class BasicAuthenticationSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /**
         * Normally, in every request to http;
         * There's a preflight request.
         * It's like an another request to clairfy the upcoming REST reuqest.
         * But if preflight request will be failed, any REST request won't be fullfilled.
         * But the method we write like this way, we'll ensuring our requests. with our authorization on react.
         */

        return http
                .authorizeHttpRequests(
                    auth -> auth
                            .mvcMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                            .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(
                    session -> session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS))
                .csrf().disable()
                .build();


    }
}

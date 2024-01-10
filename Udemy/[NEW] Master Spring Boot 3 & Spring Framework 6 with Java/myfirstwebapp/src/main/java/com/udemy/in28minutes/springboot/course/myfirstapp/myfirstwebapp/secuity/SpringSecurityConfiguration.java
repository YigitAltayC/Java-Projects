package com.udemy.in28minutes.springboot.course.myfirstapp.myfirstwebapp.secuity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;
import java.util.function.Function;

/**
 * Classes with @Configuration are automaticly called and executed depending to the situation.
 * It contains bean functions, that will be created under spring system.
 *
 */
@Configuration
public class SpringSecurityConfiguration {

    /**
     * Function to add spesific users to the system.
     * @return
     */
    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {

        UserDetails userDetails_1 = createNewUser("root", "1234");
        UserDetails userDetails_2 = createNewUser("yiit", "bjk123321");

        return new InMemoryUserDetailsManager(userDetails_1, userDetails_2);
    }

    /**
     * Function to create a new user to the spring boot system.
     * @param username
     * @param password
     * @return
     */
    private UserDetails createNewUser(String username, String password)
    {
        Function<String, String> passwordEncoder
                = input -> passwordEncoder().encode(input);

        return User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Function to enable working Frame systems on your spring boot.
     * Systems like hibernate/h2 works under a frame consol, in localhost.
     * After you use this fuction, you can make it work under your system
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );
        http.formLogin(withDefaults());

        http.csrf(csrf -> csrf.disable());
        http.headers(header -> header.frameOptions(frameOptions ->
                frameOptions.disable()));


        return http.build();
    }
}

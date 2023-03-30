package com.firstapp.springboot.myfirstwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager createUserdetailsManager() {
        String username = "kasra";
        String password = "farrokhi";


        UserDetails userDetails1 = createNewUsers("kasra", "farrokhi");
        UserDetails userDetails2 = createNewUsers("sarah", "mance");

        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails createNewUsers(String username, String password) {
        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
        UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder).username(username).password(password).roles("USER", "ADMIN").build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){
        try {
            http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
            http.formLogin(Customizer.withDefaults());
            http.csrf().disable();
            http.headers().frameOptions().disable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

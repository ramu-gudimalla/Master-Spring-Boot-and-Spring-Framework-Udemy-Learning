package com.springtutorial.todowebapplication.security;

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
//    LDAP or Database
//    InMemory
//    InMemoryUserDetailsManager
//    InMemoryUserDetailsManager(UserDetails... userDetails)
    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager(){
//        String username = "ram";
//        String password = "dummy";
        UserDetails userDetails1 = createNewUser("ram","password");
        UserDetails userDetails2 = createNewUser("laxman","dummy");
        return new InMemoryUserDetailsManager(userDetails1,userDetails2);
    }
    private UserDetails createNewUser(String username,String password) {
        Function<String, String> passwordEncoder =input -> passwordEncoder().encode(input);
        UserDetails userDetails = User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username).password(password)
                .roles("USER","ADMIN").build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    All URLs are protected
//    A login form is shown for unauthorized requests
//    CSRF disable
//    Frames
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());
        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

        return httpSecurity.build();
    }
}

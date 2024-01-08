package com.jungle.board;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BoardApplicationContext {
    @Bean
    public SecurityFilterChain secrityPollcy(HttpSecurity pollcy) throws Exception {
        return pollcy.csrf((csrf) -> csrf.disable())
                     .authorizeHttpRequests((auth) -> auth.anyRequest().permitAll())
                     .formLogin((login) -> login.disable())
                     .build();
    }
}
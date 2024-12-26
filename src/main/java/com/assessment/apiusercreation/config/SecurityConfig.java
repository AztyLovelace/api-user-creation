package com.assessment.apiusercreation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf -> csrf.disable()) .authorizeHttpRequests(auth -> auth
				.requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/h2-console/**").permitAll()
				.anyRequest().permitAll()
				).headers(headers -> headers.frameOptions(frame ->frame.sameOrigin()));
		

    return http.build();
		
	}
	
}

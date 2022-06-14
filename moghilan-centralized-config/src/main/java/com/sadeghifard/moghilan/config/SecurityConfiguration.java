package com.sadeghifard.moghilan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin().disable();
		http.httpBasic().disable();
		
		http.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
		.sessionFixation().migrateSession().maximumSessions(20);
		
		http.authorizeHttpRequests()
			.anyRequest()
			.authenticated();
		
		http.rememberMe().key("unique-and-secret")
			.rememberMeCookieName("remember-me-cookie-name")
			.tokenValiditySeconds(24 * 60 * 60);
		
		return http.build();
	}
}

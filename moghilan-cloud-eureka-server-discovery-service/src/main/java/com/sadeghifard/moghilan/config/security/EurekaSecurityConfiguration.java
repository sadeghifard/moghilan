package com.sadeghifard.moghilan.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class EurekaSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("/_discovery_server/**")
			.permitAll()
			.and()
			.httpBasic()
			.disable();
		http.sessionManagement()
			.sessionFixation()
			.migrateSession()
			.maximumSessions(20);
		return http.build();
	}
	
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		http.csrf()
			.disable()
			.authorizeExchange()
			.pathMatchers("/_discovery_server/**")
			.permitAll()
			.and()
			.httpBasic()
			.disable();
		return http.build();
	}

	 @Bean
	 public WebSecurityCustomizer webSecurityCustomizer() {
	        return (web) -> web.ignoring().antMatchers("/_discovery_server/**");
	 }

}
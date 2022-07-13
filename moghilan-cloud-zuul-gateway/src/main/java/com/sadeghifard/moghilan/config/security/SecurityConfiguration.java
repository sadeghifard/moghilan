package com.sadeghifard.moghilan.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

import lombok.AllArgsConstructor;


@EnableWebFluxSecurity
public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("/**")
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
			.pathMatchers("/**")
			.permitAll()
			.and()
			.httpBasic()
			.disable();
		return http.build();
	}

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/**");
    }
    
    @Bean
	public MapReactiveUserDetailsService userDetailsService() {
		UserDetails user = User.withUsername("admin")
								.password(passwordEncoder().encode("admin"))
								.roles("ADMIN")
								.username("user")
								.password(passwordEncoder().encode("user"))
								.roles("USER")
								.build();
		return new MapReactiveUserDetailsService(user);
    }
    
    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

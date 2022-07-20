package com.sadeghifard.moghilan.config.security;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.sadeghifard.moghilan.config.filter.PostWebSecurityFilter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@EnableWebFluxSecurity
public class TransactionSecurityConfiguration {
	
	private final PostWebSecurityFilter postWebSecurityFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("/transction")
			.hasAnyAuthority("ADMIN", "USER", "CREATOR")
			.anyRequest().fullyAuthenticated()
			.and()
			.httpBasic()
			.disable();
		http.sessionManagement()
			.sessionFixation()
			.migrateSession()
			.maximumSessions(20);
		http.rememberMe()
		    .key("partitionKey")
		    .tokenValiditySeconds(24 * 60 * 60);
		http.headers()
			.frameOptions()
			.sameOrigin()
			.disable();
		return http.build();
	}
	
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		http.addFilterAfter(postWebSecurityFilter, SecurityWebFiltersOrder.LAST);
		http.csrf()
			.disable()
			.authorizeExchange()
			.pathMatchers("/transaction")
			.hasAnyAuthority("ADMIN", "USER", "CREATOR")
			.anyExchange()
			.authenticated()
			.and()
			.httpBasic()
			.disable();
		return http.build();
	}

}

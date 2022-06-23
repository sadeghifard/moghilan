package com.sadeghifard.moghilan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.sadeghifard.moghilan.filters.ErrorFilter;
import com.sadeghifard.moghilan.filters.PostFilter;
import com.sadeghifard.moghilan.filters.PreFilter;
import com.sadeghifard.moghilan.filters.RouteFilter;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class MoghilanCloudZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoghilanCloudZuulGatewayApplication.class, args);
	}
	
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}

}

package com.sadeghifard.moghilan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sadeghifard.moghilan.service.HelloServiceClient;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class HelloControllerClient {
	private final HelloServiceClient helloServiceClient;
	
	@GetMapping("/say-hello")
	public String sayHello() {
		return helloServiceClient.sayHello();
	}
	

}

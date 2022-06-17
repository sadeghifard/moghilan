package com.sadeghifard.moghilan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String greeting() {
		return "Hello to the Moghilan Spring Cloud Client Discovery Service from EurekaClient!";
	}

}

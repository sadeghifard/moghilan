package com.sadeghifard.moghilan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.sadeghifard.moghilan.service.HelloClientService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HelloWebController {
	
	private HelloClientService helloClientService;
	
	@GetMapping("/")
	public String callme(){
		return "index";
	}
	
	@GetMapping("/say-hello")
	public String sayHello(ModelMap model){

		model.put("message", helloClientService.sayHello());
		return "hello";
	}
	
}

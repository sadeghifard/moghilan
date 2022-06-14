package com.sadeghifard.moghilan.controller.kafka;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sadeghifard.moghilan.repository.MessageRepository;
import com.sadeghifard.moghilan.service.kafka.MessageProducer;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class KafkaController {

	private final MessageProducer producer;
	private final MessageRepository messageRepository;
	
	@GetMapping("/send")
	public String sendMessage(@RequestParam("msg") String message) {
		producer.sendMessage(message);
		return message;
	}
	
	@GetMapping("/getAll")
	public String getAllMessages() {
		return messageRepository.getAllMessages();
	}
}

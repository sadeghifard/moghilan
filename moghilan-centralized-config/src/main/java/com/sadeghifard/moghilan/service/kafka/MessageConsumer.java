package com.sadeghifard.moghilan.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sadeghifard.moghilan.repository.MessageRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class MessageConsumer {

	private final MessageRepository messageRepository;
	
	@KafkaListener(topics = "${moghilan.kafka.topic}", groupId = "msg1")
	public void consume(String message) {
		log.info("MESSAGE RECEIVED AT CONSUMER END -> " + message);
		messageRepository.addMessage(message);
	}

}

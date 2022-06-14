package com.sadeghifard.moghilan.service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sadeghifard.moghilan.repository.MessageRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service

@AllArgsConstructor
public class MessageProducer {
	
	private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${moghilan.kafka.topic}")
    private String topic;
    
    public void sendMessage(String message) {
       // log.info("MESSAGE SENT FROM PRODUCER END -> " + message);
        kafkaTemplate.send(topic, message);
     }

}

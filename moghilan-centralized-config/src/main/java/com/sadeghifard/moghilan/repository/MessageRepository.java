package com.sadeghifard.moghilan.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MessageRepository {
	
	private List<String> messages = new ArrayList<>();
	
	public void addMessage(String message) {
		messages.add(message);
	}
	
	public String getAllMessages() {
		return messages.toString();
	}
}

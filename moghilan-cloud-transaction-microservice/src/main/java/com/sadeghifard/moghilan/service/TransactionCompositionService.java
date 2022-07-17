package com.sadeghifard.moghilan.service;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.sadeghifard.moghilan.utile.Utility;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TransactionCompositionService {
	private Object payload;			// TO DO: An instance of account, customer or payment
	private String partitionKey;	// TO DO: Token generator set on Header
	
	
	@InboundChannelAdapter(channel = Source.OUTPUT, autoStartup = "true")
    public GenericMessage<?> generate() throws Exception {
        String value = Utility.tokenGenerator();
        return (GenericMessage<?>) MessageBuilder.withPayload(payload).setHeader(partitionKey, value).build();
    }

}

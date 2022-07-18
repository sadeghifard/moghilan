package com.sadeghifard.moghilan.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentType {
	WITHDRAW("withdraw"), 
	DEPOSIT("deposit"), 
	BORROW("borrow");
	
	private String type;
	
	PaymentType(String type) {
		this.type = type;
	}
	
	@JsonValue
	public String getType() {
		return type;
	}
}

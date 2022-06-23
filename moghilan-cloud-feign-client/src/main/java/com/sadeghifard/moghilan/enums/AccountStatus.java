package com.sadeghifard.moghilan.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountStatus {
	ACTIVE("ACTIVE"),
	BLOCKED("BLOCKED");
	
	private String type;
	
	AccountStatus(String type) {
		this.type = type;
	}
	
	@JsonValue
	public String getType() {
		return type;
	}
}

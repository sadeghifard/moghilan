package com.sadeghifard.moghilan.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EventType {
	COMMIT("commit"),
	ROLLBACK("rollback");
	
	private String type;
	
	EventType(String type) {
		this.type = type;
	}
	
	@JsonValue
	public String getType() {
		return type;
	}
}

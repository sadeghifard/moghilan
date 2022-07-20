package com.sadeghifard.moghilan.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EventType {
	GET("get"),
	SAVE("save"),
	UPDATE("update"),
	DELETE("delete"),
	COMMITED("commited"),
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

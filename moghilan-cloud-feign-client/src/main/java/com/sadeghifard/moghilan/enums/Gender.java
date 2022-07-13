package com.sadeghifard.moghilan.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
	MALE("MALE"), 
	FEMALE("FEMALE");
	
	private String type;
	
	Gender(String type) {
		this.type = type;
	}
	
	@JsonValue
	public String getType() {
		return type;
	}
}

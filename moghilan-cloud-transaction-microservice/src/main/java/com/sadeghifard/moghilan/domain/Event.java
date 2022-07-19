package com.sadeghifard.moghilan.domain;

import com.sadeghifard.moghilan.enums.EventType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event<K,T> {
	private EventType type;
	private K key;
	private T data;
}

package com.sadeghifard.moghilan.utile;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

public class Utility {

	public static String tokenGenerator() {
		String tokenValue = UUID.randomUUID().toString();
		String strUpperCase = tokenValue.substring(5,tokenValue.length()-5);
		strUpperCase = strUpperCase.toUpperCase();
		
		SecureRandom secureRandom = new SecureRandom();
		Long randLong = secureRandom.nextLong();
		String strRand = randLong.toString();
		tokenValue = strUpperCase.concat(strRand).concat(tokenValue);
		return tokenValue;
	}
	
	public static LocalDate convertDateToLocalDate(Date date) {
	    return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
	
	public static LocalDateTime convertDateToLocalDateTime(Date date) {
	    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
	
	public static Date convertLocalDateToDate(LocalDate localDate) {
	    return java.util.Date.from(localDate.atStartOfDay()
	    					 .atZone(ZoneId.systemDefault())
	    					 .toInstant());
	}
	
	public static Date convertLocalDateTimeToDate(LocalDateTime dateTime) {
	    return java.util.Date
	    				.from(dateTime.atZone(ZoneId.systemDefault())
	    				.toInstant());
	}
}

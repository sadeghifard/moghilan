package com.sadeghifard.moghilan.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.sadeghifard.moghilan.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable{
	
	private Long id;
	private Long customerNumber;
	private String firstName;
	private String lastName;
	private Long nationalCode;
	private Gender gender;
	private String phoneNumber;
	private String email;
	private String address;
	private Boolean active;
	private Boolean loanActive;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
}

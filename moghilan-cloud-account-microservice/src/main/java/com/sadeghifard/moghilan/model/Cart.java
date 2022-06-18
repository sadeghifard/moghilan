package com.sadeghifard.moghilan.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cart_no", nullable = false)
	private Long cartNumber;
		
	@Column(name = "cvv2")
	private Integer cvv2;
	
	@Column(name = "expire_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate expireDate;
	
	@Column(name = "withdraw_limit")
	private long withdrawLimit;
		
	@Column(name = "active")
	private Boolean active;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Account.class)
	@JoinColumn(name = "acount_id", referencedColumnName = "id")
	private Account account;
	
	@Column(name = "create_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createDate;
	
	@Column(name = "modify_date", nullable = false, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime modifyDate;
}

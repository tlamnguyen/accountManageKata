package com.tienlam.kata.account.manage.entity;

import java.math.BigDecimal;

/**
 * 
 * @author Lam.NGUYEN
 * Represents the bank account of 1 client
 *
 */
public class Account {
	
	//in the real case, this value is auto increased
	private Integer id;
	private BigDecimal balance;
	
	public Account(int id) {
		this.setId(id);
		this.balance = new BigDecimal(0);
	}
	
	//When create an account, its balance maybe negatif !!!!!
	public Account(int id, BigDecimal balance) {
		this.setId(id);
		this.balance = balance;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
		
}

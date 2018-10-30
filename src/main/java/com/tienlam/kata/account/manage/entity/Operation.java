package com.tienlam.kata.account.manage.entity;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tienlam.kata.account.manage.entity.Account;

/**
 * 
 * @author Lam.NGUYEN
 * Represents the operation of bank account of 1 client
 * This class take the date, typeOperation, amount and balance for each operation
 */
public class Operation {
	//int the real case with database, this value is unique and auto increased
	private Integer id;
	private Date dateOperation;
	private BigDecimal balance;
	private String description;
	private BigDecimal amount;
	private Account account;
	
	//use this format helping us to make the test easier
	//this can be changed by client's requirement
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
	
	public Operation(int id, Date dateOperation, BigDecimal balance, String description, BigDecimal amount, Account account) {
		super();
		this.setId(id);
		this.dateOperation = dateOperation;
		this.balance = balance;
		this.description = description;
		this.amount = amount;
		this.setAccount(account);
	}
	
	public Date getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getDetails() {
		return  "Operation : " + description + " | Date : " + dateFormat.format(dateOperation) +" | Amount : " +String.format( "%.2f", amount ) + " | Balance : " + String.format( "%.2f", balance ) ;
	}
	
}


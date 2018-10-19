package com.tienlam.kata.accountManage.entity;
import java.util.Date;

import com.tienlam.kata.accountManage.entity.Account;

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
	private double balance;
	private String description;
	private double amount;
	private Account account;
 
	
	public Operation(int id, Date dateOperation, double balance, String description, double amount, Account account) {
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
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
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
		return  "Operation details- Statement";
	}
	
}


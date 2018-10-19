package com.tienlam.kata.accountManage.entity;

/**
 * 
 * @author Lam.NGUYEN
 * Represents the bank account of 1 client
 *
 */
public class Account {
	
	//in the real case, this value is auto increased
	private Integer id;
	private double balance;
	
	public Account(int id) {
		this.setId(id);
		this.balance = 0;
	}
	
	//When create an account, its balance maybe negatif !!!!!
	public Account(int id, double balance) {
		this.setId(id);
		this.balance = balance;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
		
}

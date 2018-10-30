package com.tienlam.kata.account.manage.service;

import java.math.BigDecimal;

import com.tienlam.kata.account.manage.entity.Account;

/**
 * 
 * @author Lam.NGUYEN
 * We can use the Factory Design pattern for this operation  --> not for this moment
 * In the future, if we have many client's type (Client public, client prive, client gold), who share the same operation
 * (deposit, withdrawal, transfert, loan, ....)
 */
public interface AccountManageService {

    /**
     * Create a Account
     * @return Instance of Account
     */
	public Account createAccount();
	 
	/**
     * Create a Account
     * @param balance : the initialization montant of Account
     * @return Instance of Account
     */
	public Account createAccount(BigDecimal balance);
	
	/**
     * Deposit money to account
     * If Amount deposit is negatif --> raise Exception
     * @param balance : the initialization montant of Account
     * @param account : account affected
     * @return Instance of Account after deposit
     */
	public Account deposit(BigDecimal amount, Account account);
	
	/**
     * Get current balance of account, if the amount is negatif, this function show an exception
     * @param account : account affected
     * @return current balance of account
     */
	public BigDecimal getBalance(Account account);

	/**
     * Withdrawal money from account , if the amount > balance , this function show an exception 
     * @param account : account affected
     * @return Instance of account after withdrawal
     */
	public Account withdrawal(BigDecimal amount, Account account);
	
}
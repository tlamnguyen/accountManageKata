package com.tienlam.kata.accountManage.service;

import com.tienlam.kata.accountManage.entity.Account;

/**
 * 
 * @author Lam.NGUYEN
 *
 */
public interface AccountManageService {

    /**
     * Create a Account
     * @return {Instance of Account}
     */
	public Account createAccount();
	 
	/**
     * Create a Account
     * @param balance : the initialization montant of Account
     * @return Instance of Account
     */
	public Account createAccount(double balance);
	
	/**
     * Deposit money to account
     * If Amount deposit is negatif --> raise Exception
     * @param balance : the initialization montant of Account
     * @param account : account affected
     * @return Instance of Account after deposit
     */
	public Account deposit(double amount, Account account);
	
	/**
     * Get current balance of account
     * @param account : account affected
     * @return current balance of account
     */
	public double getBalance(Account account);

	
}
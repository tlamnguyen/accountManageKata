package com.tienlam.kata.accountManage.serviceImp;


import com.tienlam.kata.accountManage.exception.AccountException;
import com.tienlam.kata.accountManage.tools.FakeDatabase;

import java.util.Date;

import com.tienlam.kata.accountManage.service.OperationManageService;
import com.tienlam.kata.accountManage.service.ServiceManageFactory;
import com.tienlam.kata.accountManage.entity.Account;
import com.tienlam.kata.accountManage.service.*;
/**
 * 
 * @author Lam.NGUYEN
 *  Implementation interface AccountManage
 */
public class AccountManageServiceImp implements AccountManageService{

	private OperationManageService operationService = ServiceManageFactory.createOperationManage();
	@Override
	public Account createAccount() {
		
		//auto get size fake database +1 --> id of new Account
		Account account =  new Account(FakeDatabase.listAccount.size() +1);
		FakeDatabase.listAccount.add(account);
		//create new operation 
		operationService.createOperation(new Date(), "Create account", account.getBalance(), 0, account);
		return account;
	}
	@Override
	public Account createAccount(double balance) {
		
		Account account =  new Account(FakeDatabase.listAccount.size() +1, balance);
		FakeDatabase.listAccount.add(account);
		operationService.createOperation(new Date(), "Create account", account.getBalance(), 0, account);
		return account;
	}
	@Override
	public Account deposit(double amount, Account account) {
	
		//if the Amount deposit is negatif --> raise an exception
		if(amount < 0) {
			throw new AccountException("Amount deposit is negatif : " + amount);
		}
		account.setBalance(account.getBalance() + amount);
		operationService.createOperation(new Date(), "Deposit", account.getBalance(), amount, account);
		return account;
	}
	@Override
	public double getBalance(Account account) {
		
		return account.getBalance();
	}
	@Override
	public Account withdrawal(double amount, Account account) {
		if(amount > account.getBalance()) {
			throw new AccountException("Amount withdrawl is bigger than balance :" + amount);
		}
		account.setBalance(account.getBalance() - amount);
		operationService.createOperation(new Date(), "Withdrawal", account.getBalance(), amount, account);
		return account;
	}

}

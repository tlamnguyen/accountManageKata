package com.tienlam.kata.account.manage.serviceImp;


import com.tienlam.kata.account.manage.entity.Account;
import com.tienlam.kata.account.manage.exception.AccountException;
import com.tienlam.kata.account.manage.service.*;
import com.tienlam.kata.account.manage.tools.FakeDatabase;
import com.tienlam.kata.account.manage.tools.OperationLabel;

import java.util.Date;
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
		Account account =  new Account(FakeDatabase.getListAccount().size() +1);
		FakeDatabase.addAccount(account);
		//create new operation 
		operationService.createOperation(new Date(), OperationLabel.CREATE_ACCOUNT, account.getBalance(), 0, account);
		return account;
	}
	@Override
	public Account createAccount(double balance) {
		
		Account account =  new Account(FakeDatabase.getListAccount().size() +1, balance);
		FakeDatabase.addAccount(account);
		operationService.createOperation(new Date(), OperationLabel.CREATE_ACCOUNT, account.getBalance(), 0, account);
		return account;
	}
	@Override
	public Account deposit(double amount, Account account) {
	
		//if the Amount deposit is negatif --> raise an exception
		if(amount < 0) {
			throw new AccountException("Amount deposit is negatif : " + amount);
		}
		account.setBalance(account.getBalance() + amount);
		operationService.createOperation(new Date(), OperationLabel.DEPOSIT, account.getBalance(), amount, account);
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
		operationService.createOperation(new Date(), OperationLabel.WITHDRAWAL, account.getBalance(), amount, account);
		return account;
	}

}

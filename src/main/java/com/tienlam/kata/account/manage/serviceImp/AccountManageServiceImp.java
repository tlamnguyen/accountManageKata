package com.tienlam.kata.account.manage.serviceImp;


import com.tienlam.kata.account.manage.entity.Account;
import com.tienlam.kata.account.manage.exception.AccountException;
import com.tienlam.kata.account.manage.service.*;
import com.tienlam.kata.account.manage.tools.FakeDatabase;
import com.tienlam.kata.account.manage.tools.OperationLabel;

import java.math.BigDecimal;
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
		operationService.createOperation(new Date(), OperationLabel.CREATE_ACCOUNT.getOperationDescription(), account.getBalance(), new BigDecimal(0), account);
		return account;
	}
	@Override
	public Account createAccount(BigDecimal balance) {
		
		Account account =  new Account(FakeDatabase.getListAccount().size() +1, balance);
		FakeDatabase.addAccount(account);
		operationService.createOperation(new Date(), OperationLabel.CREATE_ACCOUNT.getOperationDescription(), account.getBalance(), new BigDecimal(0), account);
		return account;
	}
	@Override
	public Account deposit(BigDecimal amount, Account account) {
	
		//if the Amount deposit is negatif --> raise an exception
		if(amount.compareTo(new BigDecimal(0)) < 0) {
			throw new AccountException("Amount deposit is negatif : " + amount);
		}
		account.setBalance(account.getBalance().add(amount));
		operationService.createOperation(new Date(), OperationLabel.DEPOSIT.getOperationDescription(), account.getBalance(), amount, account);
		return account;
	}
	@Override
	public BigDecimal getBalance(Account account) {
		
		return account.getBalance();
	}
	@Override
	public Account withdrawal(BigDecimal amount, Account account) {
		if(amount.compareTo(account.getBalance()) > 0) {
			throw new AccountException("Amount withdrawl is bigger than balance :" + amount);
		}
		account.setBalance(account.getBalance().subtract(amount));
		operationService.createOperation(new Date(), OperationLabel.WITHDRAWAL.getOperationDescription(), account.getBalance(), amount, account);
		return account;
	}

}

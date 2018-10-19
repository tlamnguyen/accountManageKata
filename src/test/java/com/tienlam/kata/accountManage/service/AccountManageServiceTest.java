package com.tienlam.kata.accountManage.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.tienlam.kata.accountManage.entity.Account;
import com.tienlam.kata.accountManage.exception.AccountException;
import com.tienlam.kata.accountManage.service.AccountManageService;
import com.tienlam.kata.accountManage.service.ServiceManageFactory;

/**
 * 
 * @author Lam.NGUYEN
 * Test interface AccountManage 
 */
public class AccountManageServiceTest {
	private Account accountDeposit;
	private AccountManageService accountManageService;
	
	@Before
	public void initTest() {
		accountManageService = ServiceManageFactory.createAccountManage();
		//Account used for test function deposit
		accountDeposit = accountManageService.createAccount();		

	}

	@Test
	public void createAccountWithoutBalance() {
		Account newAccount = accountManageService.createAccount();
		assertEquals(0, accountManageService.getBalance(newAccount), 0);
	}
	
	@Test
	public void createAccountWithBalancePositif() {
		Account newAccount = accountManageService.createAccount(200);
		assertEquals(200, accountManageService.getBalance(newAccount), 0);
	}
	
	@Test
	public void createAccountWithBalanceNegatif() {
		Account newAccount = accountManageService.createAccount(-200);
		assertEquals(-200, accountManageService.getBalance(newAccount), 0);
	}
	
	@Test
	public void depositEqualZero() {
		accountManageService.deposit((double)0, accountDeposit);
		assertEquals(0, accountManageService.getBalance(accountDeposit), 0);
	}
	
	@Test
	public void depositMultiTimes() {
		accountManageService.deposit((double)100, accountDeposit);
		accountManageService.deposit((double)200, accountDeposit);
		accountManageService.deposit((double)200, accountDeposit);
		accountManageService.deposit((double)200, accountDeposit);
		assertEquals(700, accountManageService.getBalance(accountDeposit), 0);
	}
	
	@Test
	public void depositABigNumber() {
		accountManageService.deposit((double)50000, accountDeposit);
		assertEquals(50000, accountManageService.getBalance(accountDeposit), 0);
	}
	
	@Test
	public void depositWithComma() {
		accountManageService.deposit((double)5000.25, accountDeposit);
		assertEquals(5000.25, accountManageService.getBalance(accountDeposit), 0);
	}
	
	@Test(expected = AccountException.class)
	public void depositWithNegatifAmount() {
		accountManageService.deposit((double)-100.20, accountDeposit);
		//exception raise
	}
	
}

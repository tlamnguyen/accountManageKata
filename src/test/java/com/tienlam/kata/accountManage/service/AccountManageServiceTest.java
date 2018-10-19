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
	private Account accountWithDrawal;
	private AccountManageService accountManageService;
	
	@Before
	public void initTest() {
		accountManageService = ServiceManageFactory.createAccountManage();
		//Account used for test function deposit
		accountDeposit = accountManageService.createAccount();		
		//Account used for test function withdrawal
		accountWithDrawal = accountManageService.createAccount(100);

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
	//test for function withdrawal
	@Test
	public void withdrawalEqualZeroAmount() {
		accountManageService.withdrawal((double)0, accountWithDrawal);
		assertEquals(100, accountManageService.getBalance(accountWithDrawal), 0);
	}
	
	@Test
	public void withdrawalToBalanceZero() {
		accountManageService.withdrawal((double)100, accountWithDrawal);
		assertEquals(0, accountManageService.getBalance(accountWithDrawal), 0);
	}
	
	@Test(expected = AccountException.class)
	public void withdrawalAmountBiggerThanBalance() {
		accountManageService.withdrawal((double)2000, accountWithDrawal);
		//exception raise
	
	}
	
	@Test
	public void withdrawalMultiTimes() {
		accountManageService.withdrawal((double)10, accountWithDrawal);
		accountManageService.withdrawal((double)15, accountWithDrawal);
		accountManageService.withdrawal((double)20, accountWithDrawal);
		assertEquals(55, accountManageService.getBalance(accountWithDrawal), 0);
	}
	
	@Test
	public void withdrawalAmountLessThanBalance() {
		accountManageService.withdrawal((double)10, accountWithDrawal);
		assertEquals(90, accountManageService.getBalance(accountWithDrawal), 0);
	}
	
	@Test
	public void withdrawalWithComma() {
		accountManageService.withdrawal((double)10.5, accountWithDrawal);
		assertEquals(89.5, accountManageService.getBalance(accountWithDrawal), 0);
	}
	
	@Test
	public void DepositAndWithdrawalManyTimes() {
		Account accountTest = accountManageService.createAccount(100);
		accountManageService.withdrawal((double)50, accountTest);
		accountManageService.deposit((double)20, accountTest);
		accountManageService.deposit((double)60, accountTest);
		accountManageService.withdrawal((double)80, accountTest);
		assertEquals(50, accountManageService.getBalance(accountTest), 0);
	}
}

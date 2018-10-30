package com.tienlam.kata.account.manage.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.tienlam.kata.account.manage.entity.Account;
import com.tienlam.kata.account.manage.exception.AccountException;
import com.tienlam.kata.account.manage.service.AccountManageService;
import com.tienlam.kata.account.manage.service.ServiceManageFactory;

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
		accountWithDrawal = accountManageService.createAccount(new BigDecimal(100));

	}

	@Test
	public void createAccountWithoutBalance() {
		Account newAccount = accountManageService.createAccount();
		assertEquals(new BigDecimal(0), accountManageService.getBalance(newAccount));
	}
	
	@Test
	public void createAccountWithBalancePositif() {
		Account newAccount = accountManageService.createAccount(new BigDecimal(200));
		assertEquals(new BigDecimal(200), accountManageService.getBalance(newAccount));
	}
	
	@Test
	public void createAccountWithBalanceNegatif() {
		Account newAccount = accountManageService.createAccount(new BigDecimal(-200));
		assertEquals(new BigDecimal(-200), accountManageService.getBalance(newAccount));
	}
	
	@Test
	public void depositEqualZero() {
		accountManageService.deposit(new BigDecimal(0), accountDeposit);
		assertEquals(new BigDecimal(0), accountManageService.getBalance(accountDeposit));
	}
	
	@Test
	public void depositMultiTimes() {
		accountManageService.deposit(new BigDecimal(100), accountDeposit);
		accountManageService.deposit(new BigDecimal(200), accountDeposit);
		accountManageService.deposit(new BigDecimal(200), accountDeposit);
		accountManageService.deposit(new BigDecimal(200), accountDeposit);
		assertEquals(new BigDecimal(700), accountManageService.getBalance(accountDeposit));
	}
	
	@Test
	public void depositABigNumber() {
		accountManageService.deposit(new BigDecimal(50000), accountDeposit);
		assertEquals(new BigDecimal(50000), accountManageService.getBalance(accountDeposit));
	}
	
	@Test
	public void depositWithComma() {
		accountManageService.deposit(new BigDecimal(5000.25), accountDeposit);
		assertEquals(new BigDecimal(5000.25), accountManageService.getBalance(accountDeposit));
	}
	
	@Test(expected = AccountException.class)
	public void depositWithNegatifAmount() {
		accountManageService.deposit(new BigDecimal(-100.20), accountDeposit);
		//exception raise
	}
	//test for function withdrawal
	@Test
	public void withdrawalEqualZeroAmount() {
		accountManageService.withdrawal(new BigDecimal(0), accountWithDrawal);
		assertEquals(new BigDecimal(100), accountManageService.getBalance(accountWithDrawal));
	}
	
	@Test
	public void withdrawalToBalanceZero() {
		accountManageService.withdrawal(new BigDecimal(100), accountWithDrawal);
		assertEquals(new BigDecimal(0), accountManageService.getBalance(accountWithDrawal));
	}
	
	@Test(expected = AccountException.class)
	public void withdrawalAmountBiggerThanBalance() {
		accountManageService.withdrawal(new BigDecimal(200), accountWithDrawal);
		//exception raise
	
	}
	
	@Test
	public void withdrawalMultiTimes() {
		accountManageService.withdrawal(new BigDecimal(10), accountWithDrawal);
		accountManageService.withdrawal(new BigDecimal(15), accountWithDrawal);
		accountManageService.withdrawal(new BigDecimal(20), accountWithDrawal);
		assertEquals(new BigDecimal(55), accountManageService.getBalance(accountWithDrawal));
	}
	
	@Test
	public void withdrawalAmountLessThanBalance() {
		accountManageService.withdrawal(new BigDecimal(10), accountWithDrawal);
		assertEquals(new BigDecimal(90), accountManageService.getBalance(accountWithDrawal));
	}
	
	@Test
	public void withdrawalWithComma() {
		accountManageService.withdrawal(new BigDecimal(10.5), accountWithDrawal);
		assertEquals(new BigDecimal(89.5), accountManageService.getBalance(accountWithDrawal));
	}
	
	@Test
	public void DepositAndWithdrawalManyTimes() {
		Account accountTest = accountManageService.createAccount(new BigDecimal(100));
		accountManageService.withdrawal(new BigDecimal(50), accountTest);
		accountManageService.deposit(new BigDecimal(20), accountTest);
		accountManageService.deposit(new BigDecimal(60), accountTest);
		accountManageService.withdrawal(new BigDecimal(80), accountTest);
		assertEquals(new BigDecimal(50), accountManageService.getBalance(accountTest));
	}
}

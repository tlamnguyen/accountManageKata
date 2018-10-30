package com.tienlam.kata.account.manage.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
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
		//assertEquals(new BigDecimal(0), accountManageService.getBalance(newAccount));
		assertThat(new BigDecimal(0),  Matchers.comparesEqualTo(accountManageService.getBalance(newAccount)));
	}
	
	@Test
	public void createAccountWithBalancePositif() {
		Account newAccount = accountManageService.createAccount(new BigDecimal(200));
		assertThat(new BigDecimal(200),  Matchers.comparesEqualTo(accountManageService.getBalance(newAccount)));
	}
	
	@Test
	public void createAccountWithBalanceNegatif() {
		Account newAccount = accountManageService.createAccount(new BigDecimal(-200));
		assertThat(new BigDecimal(-200),  Matchers.comparesEqualTo(accountManageService.getBalance(newAccount)));
	}
	
	@Test
	public void depositEqualZero() {
		accountManageService.deposit(new BigDecimal(0), accountDeposit);
		assertThat(new BigDecimal(0),  Matchers.comparesEqualTo(accountManageService.getBalance(accountDeposit)));
	}
	
	@Test
	public void depositMultiTimes() {
		accountManageService.deposit(new BigDecimal(100), accountDeposit);
		accountManageService.deposit(new BigDecimal(200), accountDeposit);
		accountManageService.deposit(new BigDecimal(200), accountDeposit);
		accountManageService.deposit(new BigDecimal(200), accountDeposit);
		assertThat(new BigDecimal(700),  Matchers.comparesEqualTo(accountManageService.getBalance(accountDeposit)));
	}
	
	@Test
	public void depositABigNumber() {
		accountManageService.deposit(new BigDecimal(50000), accountDeposit);
		assertThat(new BigDecimal(50000),  Matchers.comparesEqualTo(accountManageService.getBalance(accountDeposit)));
	}
	
	@Test
	public void depositWithComma() {
		accountManageService.deposit(new BigDecimal(5000.25), accountDeposit);
		assertThat(new BigDecimal(5000.25),  Matchers.comparesEqualTo(accountManageService.getBalance(accountDeposit)));
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
		assertThat(new BigDecimal(100),  Matchers.comparesEqualTo(accountManageService.getBalance(accountWithDrawal)));
	}
	
	@Test
	public void withdrawalToBalanceZero() {
		accountManageService.withdrawal(new BigDecimal(100), accountWithDrawal);
		assertThat(new BigDecimal(0),  Matchers.comparesEqualTo(accountManageService.getBalance(accountWithDrawal)));
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
		assertThat(new BigDecimal(55),  Matchers.comparesEqualTo(accountManageService.getBalance(accountWithDrawal)));
	}
	
	@Test
	public void withdrawalAmountLessThanBalance() {
		accountManageService.withdrawal(new BigDecimal(10), accountWithDrawal);
		assertThat(new BigDecimal(90),  Matchers.comparesEqualTo(accountManageService.getBalance(accountWithDrawal)));
	}
	
	@Test
	public void withdrawalWithComma() {
		accountManageService.withdrawal(new BigDecimal(10.5), accountWithDrawal);
		assertThat(new BigDecimal(89.5),  Matchers.comparesEqualTo(accountManageService.getBalance(accountWithDrawal)));
	}
	
	@Test
	public void DepositAndWithdrawalManyTimes() {
		Account accountTest = accountManageService.createAccount(new BigDecimal(100));
		accountManageService.withdrawal(new BigDecimal(50), accountTest);
		accountManageService.deposit(new BigDecimal(20), accountTest);
		accountManageService.deposit(new BigDecimal(60), accountTest);
		accountManageService.withdrawal(new BigDecimal(80), accountTest);
		assertThat(new BigDecimal(50),  Matchers.comparesEqualTo(accountManageService.getBalance(accountTest)));
	}
}

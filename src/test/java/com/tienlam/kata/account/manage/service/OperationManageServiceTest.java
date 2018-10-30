package com.tienlam.kata.account.manage.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.tienlam.kata.account.manage.entity.Account;
import com.tienlam.kata.account.manage.service.AccountManageService;
import com.tienlam.kata.account.manage.service.OperationManageService;
import com.tienlam.kata.account.manage.service.ServiceManageFactory;

public class OperationManageServiceTest {
	private Account accountOperation;
	private AccountManageService accountManageService;
	private OperationManageService operationManageService;
	
	@Before
	public void initTest() {
		accountManageService = ServiceManageFactory.createAccountManage();
		operationManageService = ServiceManageFactory.createOperationManage();
		//Account used for test function show statement
		accountOperation = accountManageService.createAccount(new BigDecimal(100));
	}
	@Test
	public void showHistoryWithNewAccount() {
		
		Account account = accountManageService.createAccount();
		operationManageService.getOperationByAccountOrderByIdAsc(account);
		operationManageService.printOperationByAccountOrderByIdAsc(account);
		//if there is no bug --> this function works well
	}
	@Test
	public void showHistoryWithAccountHaveManyOperations() {
		accountManageService.deposit(new BigDecimal(9999), accountOperation);
		accountManageService.withdrawal(new BigDecimal(30), accountOperation);
		accountManageService.deposit(new BigDecimal(20), accountOperation);
		accountManageService.withdrawal(new BigDecimal(560), accountOperation);
		accountManageService.withdrawal(new BigDecimal(200), accountOperation);
		operationManageService.printOperationByAccountOrderByIdAsc(accountOperation);

	}
	@Test
	public void countNbOperationWhenCreateAccount() {
		Account account = accountManageService.createAccount();
		//add another account to verify if the FakeDatabase works!!!!
		Account anotherAccount = accountManageService.createAccount();
		assertEquals(1, operationManageService.getOperationByAccountOrderByIdAsc(account).size(), 0);
	}
	@Test
	public void countNbOperationWhenAddOperation() {
		Account anotherAccount = accountManageService.createAccount();
		
		accountManageService.withdrawal(new BigDecimal(10.5), accountOperation);
		accountManageService.deposit(new BigDecimal(90.99), accountOperation);
		
		//use accountTest to verify if the function getOperation works!!!!
		accountManageService.deposit(new BigDecimal(300), anotherAccount);
		accountManageService.deposit(new BigDecimal(500.36), anotherAccount);
		accountManageService.deposit(new BigDecimal(250.00), anotherAccount);
		
		accountManageService.deposit(new BigDecimal(500), accountOperation);
		
		assertEquals(4, operationManageService.getOperationByAccountOrderByIdAsc(accountOperation).size(), 0);
		operationManageService.printOperationByAccountOrderByIdAsc(accountOperation);
	}
	@Test
	public void showDetailOperationCreateAccount() {
		Account newAccount = accountManageService.createAccount(new BigDecimal(-200));
		final String detailExpected = "Operation : Create account | Date : 19/10/2018 | Amount : 0.00 | Balance : -200.00";
		assertEquals(detailExpected, operationManageService.getOperationByAccountOrderByIdAsc(newAccount).get(0).getDetails());
	}
		
	@Test
	public void showDetailOfSecondOperationDeposit() {
		accountManageService.deposit(new BigDecimal(10.05), accountOperation);
		final String detailExpected = "Operation : Deposit | Date : 19/10/2018 | Amount : 10.50 | Balance : 110.50";
		assertEquals(detailExpected, operationManageService.getOperationByAccountOrderByIdAsc(accountOperation).get(1).getDetails());
	}
	@Test
	public void showDetailOfThirdOperationDepositWithOperationsFromAnotherAccount() {
		Account anotherAccount = accountManageService.createAccount(new BigDecimal(-90.99));
		accountManageService.deposit(new BigDecimal(20.5), accountOperation);
		accountManageService.deposit(new BigDecimal(20.5), anotherAccount);
		accountManageService.deposit(new BigDecimal(10.5), accountOperation);
		accountManageService.deposit(new BigDecimal(20.5), anotherAccount);
		accountManageService.deposit(new BigDecimal(30.5), anotherAccount);
		final String detailExpected = "Operation : Deposit | Date : 19/10/2018 | Amount : 10.50 | Balance : 131.00";
		assertEquals(detailExpected, operationManageService.getOperationByAccountOrderByIdAsc(accountOperation).get(2).getDetails());
		operationManageService.printOperationByAccountOrderByIdAsc(accountOperation);
	}
	@Test
	public void showDetailOfSecondOperationWithDrawal() {
		accountManageService.withdrawal(new BigDecimal(20), accountOperation);
		final String detailExpected ="Operation : Withdrawal | Date : 19/10/2018 | Amount : 20.00 | Balance : 80.00";
		assertEquals(detailExpected, operationManageService.getOperationByAccountOrderByIdAsc(accountOperation).get(1).getDetails());
	}
	@Test
	public void showDetailOfThirdOperationWithDrawalWithOperationsFromAnotherAccount() {
		Account anotherAccount = accountManageService.createAccount(new BigDecimal(300.88));
		accountManageService.withdrawal(new BigDecimal(30), accountOperation);
		accountManageService.withdrawal(new BigDecimal(20), accountOperation);
		accountManageService.deposit(new BigDecimal(20), anotherAccount);
		operationManageService.printOperationByAccountOrderByIdAsc(accountOperation);
		final String detailExpected ="Operation : Withdrawal | Date : 19/10/2018 | Amount : 20.00 | Balance : 50.00";
		assertEquals(detailExpected, operationManageService.getOperationByAccountOrderByIdAsc(accountOperation).get(2).getDetails());
		operationManageService.printOperationByAccountOrderByIdAsc(accountOperation);
	}
}

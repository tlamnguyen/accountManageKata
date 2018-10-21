package com.tienlam.kata.account.manage.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.tienlam.kata.account.manage.entity.Account;
import com.tienlam.kata.account.manage.entity.Operation;
/**
 * 
 * @author Lam.NGUYEN
 *  Class Fake Database with its operations
 *  Users can not modify directment from listAccount and listOperation, they have to use public function.
 */
public class FakeDatabase {
	private static final List<Account> listAccount = new ArrayList<>();
	private static final List<Operation> listOperation = new ArrayList<>();
	
	public static List<Account> getListAccount(){
		List<Account> copyListAccount = listAccount.stream().collect(Collectors.toList());
		return copyListAccount;
	}
	
	public static List<Operation> getListOperation(){
		List<Operation> copyListOperation = listOperation.stream().collect(Collectors.toList());
		return copyListOperation;
	}
	
	public static void addAccount(Account account) {
		if(account!= null && !isAccountExists(account)) {
			listAccount.add(account);
		}
	}
	
	public static void addOperation(Operation operation) {
		if(operation != null && !isOperationExists(operation)) {
			listOperation.add(operation);
		}
	}
	
	public static boolean isAccountExists(Account accountTest) {
		return getListAccount().stream().anyMatch(account -> account.getId().equals(accountTest.getId()));
	}
	
	public static boolean isOperationExists(Operation operationTest) {
		return getListOperation().stream().anyMatch(operation -> operation.getId().equals(operationTest.getId()));
	}
	
	
}
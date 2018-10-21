package com.tienlam.kata.account.manage.service;

import com.tienlam.kata.account.manage.service.AccountManageService;
import com.tienlam.kata.account.manage.serviceImp.*;
/**
 * 
 * @author Lam.NGUYEN
 *  interface use for creating AccountManageService
 */
public interface ServiceManageFactory {
	/**
	 * Create a AccountManageService
	 *
	 * @return {@link AccountManageService}
	 */
	public static AccountManageService createAccountManage() {
		return new AccountManageServiceImp();
	}
	
	/**
	 * Create a AccountManageService
	 *
	 * @return {@link AccountManageService}
	 */
	public static OperationManageService createOperationManage() {
		return new OperationManageServiceImp();
	}
}

package com.tienlam.kata.accountManage.service;

import com.tienlam.kata.accountManage.service.AccountManageService;
import com.tienlam.kata.accountManage.serviceImp.*;
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
}

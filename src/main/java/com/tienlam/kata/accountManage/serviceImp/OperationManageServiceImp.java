package com.tienlam.kata.accountManage.serviceImp;

import java.util.Date;
import java.util.List;

import com.tienlam.kata.accountManage.entity.Account;
import com.tienlam.kata.accountManage.entity.Operation;
import com.tienlam.kata.accountManage.service.OperationManageService;
/**
 * 
 * @author Lam.NGUYEN
 *  Implementation interface OperationManageService
 */
public class OperationManageServiceImp implements OperationManageService{

	@Override
	public Operation createOperation(Date dateCreate, String typeOperation, double balance, double amount,
			Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Operation> getOperationByAccountOrderByIdAsc(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printOperationByAccountOrderByIdAsc(Account account) {
		// TODO Auto-generated method stub
		
	}

}

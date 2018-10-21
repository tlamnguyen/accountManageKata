package com.tienlam.kata.account.manage.serviceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.tienlam.kata.account.manage.entity.Account;
import com.tienlam.kata.account.manage.entity.Operation;
import com.tienlam.kata.account.manage.service.OperationManageService;
import com.tienlam.kata.account.manage.tools.FakeDatabase;
/**
 * 
 * @author Lam.NGUYEN
 *  Implementation interface OperationManageService
 */
public class OperationManageServiceImp implements OperationManageService{

	@Override
	public List<Operation> getOperationByAccountOrderByIdAsc(Account account) {
		
		List<Operation> listOperation  = FakeDatabase.getListOperation().stream().filter(op -> (op.getAccount().getId().equals(account.getId())))
				.sorted((op1, op2) ->{
					return op1.getId().compareTo(op2.getId()) ;
				}).collect(Collectors.toList());
		return listOperation;
	}
	@Override
	public void printOperationByAccountOrderByIdAsc(Account account) {

		List<Operation> listOperation = this.getOperationByAccountOrderByIdAsc(account);
		if(listOperation != null && !listOperation.isEmpty()) {
			listOperation.stream().forEach(operation -> System.out.println(operation.getDetails()));
		}
	}

	@Override
	public Operation createOperation(Date dateCreate, String typeOperation, double balance, double amount,
			Account account) {
		
		Operation op = new Operation(FakeDatabase.getListOperation().size() + 1, dateCreate, balance, typeOperation, amount, account);
		FakeDatabase.addOperation(op);
		return op;
	}


}

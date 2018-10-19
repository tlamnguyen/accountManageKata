package com.tienlam.kata.accountManage.serviceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.tienlam.kata.accountManage.tools.FakeDatabase;
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
	public List<Operation> getOperationByAccountOrderByIdAsc(Account account) {
		
		List<Operation> listOperation = new ArrayList<Operation>();
		listOperation = FakeDatabase.listOperation.stream().filter(op -> (op.getAccount().getId().equals(account.getId())))
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
		
		Operation op = new Operation(FakeDatabase.listOperation.size() + 1, dateCreate, balance, typeOperation, amount, account);
		FakeDatabase.listOperation.add(op);
		return op;
	}


}

package com.tienlam.kata.account.manage.serviceImp;

import java.math.BigDecimal;
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
		
		//dont need to use sorted() !
		List<Operation> listOperation  = FakeDatabase.getListOperation().stream().filter(op -> (op.getAccount().getId().equals(account.getId())))
				.collect(Collectors.toList());
		return listOperation;
	}
	@Override
	public void printOperationByAccountOrderByIdAsc(Account account) {

		FakeDatabase.getListOperation().stream().filter(op -> (op.getAccount().getId().equals(account.getId())))
		.map(op -> op.getDetails()).forEach(System.out::println);
	}

	@Override
	public Operation createOperation(Date dateCreate, String typeOperation, BigDecimal balance, BigDecimal amount,
			Account account) {
		
		Operation op = new Operation(FakeDatabase.getListOperation().size() + 1, dateCreate, balance, typeOperation, amount, account);
		FakeDatabase.addOperation(op);
		return op;
	}


}

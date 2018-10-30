package com.tienlam.kata.account.manage.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.tienlam.kata.account.manage.entity.Account;
import com.tienlam.kata.account.manage.entity.Operation;
/**
 * 
 * @author Lam.NGUYEN
 *
 */
public interface OperationManageService {

	/**
     * Create new operation
     * @param dateCreate : date create of operation
     * @param typeOperation : type of operation (CREATE / DEPOSIT / WITHDRAWAL)
     * @param balance : the balance after operation
     * @param amount : amount of operation
     * @param Account : Account affected
     * @return Operation created
     */
	public Operation createOperation(Date dateCreate, String typeOperation, BigDecimal balance, BigDecimal amount, Account account);
	
	/**
     * Get list operation by account order by Id Asc
     * @param Account : Account affected
     * @return List of Operation
     */
	public List<Operation> getOperationByAccountOrderByIdAsc(Account account);
	
	/**
     * Print all operation by account order by Id Asc
     * @param Account : Account affected
     * @return 
     */
	public void printOperationByAccountOrderByIdAsc(Account account);
}

package com.tienlam.kata.account.manage.tools;

public enum OperationLabel {
	DEPOSIT("Deposit"),
	WITHDRAWAL("Withdrawal"),
	CREATE_ACCOUNT("Create account");
	
	private String operationDescription;
	
	OperationLabel(String operationDescription){
		this.operationDescription = operationDescription;
	}
	public String getOperationDescription() {
		return this.operationDescription;
	}
}
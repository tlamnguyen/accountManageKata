package com.tienlam.kata.accountManage.exception;

/**
 * 
 * @author Lam.NGUYEN
 * Exception for class Account
 */
public class AccountException extends RuntimeException {
	public AccountException() {
    }

    public AccountException(String message) {
        super(message);
    }

    public AccountException(Throwable e) {
        super(e);
    }

    public AccountException(String message, Throwable cause) {
        super(message, cause);
    }

}

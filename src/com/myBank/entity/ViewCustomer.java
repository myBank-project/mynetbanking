package com.myBank.entity;

import com.myBank.other.AccountType;

public class ViewCustomer {

	private String customerfirstName;
	private String customerlastName;
	private int accountNumber;
	private int balance;

	public ViewCustomer(String customerfirstName, String customerlastName, int accountNumber, int balance) {
		super();
		this.customerfirstName = customerfirstName;
		this.customerlastName = customerlastName;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public String getCustomerfirstName() {
		return customerfirstName;
	}

	public void setCustomerfirstName(String customerfirstName) {
		this.customerfirstName = customerfirstName;
	}

	public String getCustomerlastName() {
		return customerlastName;
	}

	public void setCustomerlastName(String customerlastName) {
		this.customerlastName = customerlastName;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "ViewCustomer [customerfirstName=" + customerfirstName + ", customerlastName=" + customerlastName
				+ ", accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}

}

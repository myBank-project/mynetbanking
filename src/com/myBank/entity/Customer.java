package com.myBank.entity;

import java.sql.Date;

import com.myBank.other.*;

public class Customer {

	private int customerId;
	private String customerfirstName;
	private String customerlastName;
	private String customerEmail;
	private int accountNumber;
	private AccountType accountType;
	private String customerAddress;
	private String nomineeName;
	private int bankBranchId;
	private int balance;

	

	
	public Customer(int customerId, String customerfirstName, String customerlastName, String customerEmail,
			int accountNumber, AccountType accountType, String customerAddress, String nomineeName, int bankBranchId,
			int balance) {
		super();
		this.customerId = customerId;
		this.customerfirstName = customerfirstName;
		this.customerlastName = customerlastName;
		this.customerEmail = customerEmail;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.customerAddress = customerAddress;
		this.nomineeName = nomineeName;
		this.bankBranchId = bankBranchId;
		this.balance = balance;
	}

	public Customer(String firstName, String lastName, int accountNumber2, int balance2) {
		this.customerfirstName = firstName;
		this.customerlastName = lastName;
		this.accountNumber = accountNumber2;
		this.balance = balance2;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
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

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public int getBankBranchId() {
		return bankBranchId;
	}

	public void setBankBranchId(int bankBranchId) {
		this.bankBranchId = bankBranchId;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", accountType=" + accountType + ", accountNumber="
				+ accountNumber + ", customerAddress=" + customerAddress + ", nomineeName=" + nomineeName
				+ ", bankBranchId=" + bankBranchId + ", customerEmail=" + customerEmail + ", balance=" + balance + "]";
	}

}

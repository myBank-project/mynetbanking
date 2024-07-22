package com.myBank.entity;

import java.sql.Date;

import com.myBank.other.TransactionType;

public class CustomerPassbookOrViewTransaction {
	private int transactionId;
	private int senderAccountNumber;
	private int receiverAccountNumber;
	private Date transactionDate;
	private TransactionType transactionType;
	private int transactionAmount;

	public CustomerPassbookOrViewTransaction(int transactionId, int senderAccountNumber, int receiverAccountNumber, Date transactionDate,
			String transactionType, int transactionAmount) {
		super();
		this.transactionId = transactionId;
		this.senderAccountNumber = senderAccountNumber;
		this.receiverAccountNumber = receiverAccountNumber;
		this.transactionDate = transactionDate;
		this.transactionType =TransactionType.valueOf(transactionType.toUpperCase());
		this.transactionAmount = transactionAmount;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getSenderAccountNumber() {
		return senderAccountNumber;
	}

	public void setSenderAccountNumber(int senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}

	public int getReceiverAccountNumber() {
		return receiverAccountNumber;
	}

	public void setReceiverAccountNumber(int receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public int getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(int transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	@Override
	public String toString() {
		return "CustomerPassbookAndViewTransaction [transactionId=" + transactionId + ", senderAccountNumber=" + senderAccountNumber
				+ ", receiverAccountNumber=" + receiverAccountNumber + ", transactionDate=" + transactionDate
				+ ", transactionType=" + transactionType + ", transactionAmount=" + transactionAmount + "]";
	}
	
}

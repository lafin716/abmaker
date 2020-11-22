package com.lafin.abmaker.dto;

public class AccountLogList {
	private int accountLogSeq;
	private int compSeq;
	private int accountSeq;
	private String accountName;
	private String compName;
	private String type;
	private int amount;
	private String memo;
	private String registDate;
	
	public int getAccountLogSeq() {
		return accountLogSeq;
	}
	public void setAccountLogSeq(int accountLogSeq) {
		this.accountLogSeq = accountLogSeq;
	}
	public int getCompSeq() {
		return compSeq;
	}
	public void setCompSeq(int compSeq) {
		this.compSeq = compSeq;
	}
	public int getAccountSeq() {
		return accountSeq;
	}
	public void setAccountSeq(int accountSeq) {
		this.accountSeq = accountSeq;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	
	
}

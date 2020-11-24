package com.lafin.abmaker.dto;

import java.util.Date;

public class AccountDto {
	private Integer accountSeq;
	private Integer userSeq;
	private String accountName;
	private Integer amount;
	private String memo;
	private Date registDate;
	
	public Integer getAccountSeq() {
		return accountSeq;
	}
	public void setAccountSeq(Integer accountSeq) {
		this.accountSeq = accountSeq;
	}
	public Integer getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(Integer userSeq) {
		this.userSeq = userSeq;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	
}

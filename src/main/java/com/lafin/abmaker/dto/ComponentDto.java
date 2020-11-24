package com.lafin.abmaker.dto;

public class ComponentDto {
	private int compSeq;
	private int accountSeq;
	private String compName;
	private String type;
	private boolean isWritable;
	private boolean isReadonly;
	private String compContent;
	private String registDate;
	
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
	public boolean isWritable() {
		return isWritable;
	}
	public void setWritable(boolean isWritable) {
		this.isWritable = isWritable;
	}
	public boolean isReadonly() {
		return isReadonly;
	}
	public void setReadonly(boolean isReadonly) {
		this.isReadonly = isReadonly;
	}
	public String getCompContent() {
		return compContent;
	}
	public void setCompContent(String compContent) {
		this.compContent = compContent;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	
	
	
}

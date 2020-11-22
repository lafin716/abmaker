package com.lafin.abmaker.dto;

public class FormList {
	private int formSeq;
	private String formTitle;
	private boolean isMain;
	private String registDate;
	
	public int getFormSeq() {
		return formSeq;
	}
	public void setFormSeq(int formSeq) {
		this.formSeq = formSeq;
	}
	public String getFormTitle() {
		return formTitle;
	}
	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}
	public boolean isMain() {
		return isMain;
	}
	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	
	
}

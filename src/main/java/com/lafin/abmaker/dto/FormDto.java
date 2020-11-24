package com.lafin.abmaker.dto;

import java.util.Date;

public class FormDto {
	private Integer formSeq;
	private Integer userSeq;
	private String formTitle;
	private Integer isMain;
	private Date registDate;
	
	public Integer getFormSeq() {
		return formSeq;
	}
	public void setFormSeq(Integer formSeq) {
		this.formSeq = formSeq;
	}
	
	public Integer getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(Integer userSeq) {
		this.userSeq = userSeq;
	}
	public String getFormTitle() {
		return formTitle;
	}
	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}
	public Integer getIsMain() {
		return isMain;
	}
	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	
	
}

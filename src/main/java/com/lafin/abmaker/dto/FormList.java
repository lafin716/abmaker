package com.lafin.abmaker.dto;

import java.util.Date;

public class FormList {
	private Integer formSeq;
	private String formTitle;
	private Integer isMain;
	private Date registDate;
	
	public Integer getFormSeq() {
		return formSeq;
	}
	public void setFormSeq(Integer formSeq) {
		this.formSeq = formSeq;
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

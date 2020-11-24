package com.lafin.abmaker.dto;

import java.util.Date;

public class PageDto {
	private Integer pageSeq;
	private Integer formSeq;
	private String pageTitle;
	private Integer order;
	private Date registDate;
	
	public Integer getPageSeq() {
		return pageSeq;
	}
	public void setPageSeq(Integer pageSeq) {
		this.pageSeq = pageSeq;
	}
	public Integer getFormSeq() {
		return formSeq;
	}
	public void setFormSeq(Integer formSeq) {
		this.formSeq = formSeq;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	
	
}

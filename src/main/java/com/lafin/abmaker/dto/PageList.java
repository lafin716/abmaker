package com.lafin.abmaker.dto;

public class PageList {
	private int pageSeq;
	private int formSeq;
	private String pageTitle;
	private int order;
	private String registDate;
	
	public int getPageSeq() {
		return pageSeq;
	}
	public void setPageSeq(int pageSeq) {
		this.pageSeq = pageSeq;
	}
	public int getFormSeq() {
		return formSeq;
	}
	public void setFormSeq(int formSeq) {
		this.formSeq = formSeq;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	
	
}

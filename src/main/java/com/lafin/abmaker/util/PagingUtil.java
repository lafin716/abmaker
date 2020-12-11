package com.lafin.abmaker.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PagingUtil {
	private int currentPage;
	private int currentBlock;
	private int totalRow;
	private int totalPage;
	private int totalBlock;
	private int rowSize;
	private int pageSize;
	private int startRow;
	private int startPage;
	private int endPage;
	private int prevPage;
	private int nextPage;
	private int firstPage;
	private int lastPage;
	
	
	// 기본 페이징 설정 (행 : 10, 블럭당 페이지 : 5)
	public PagingUtil() {
		this.currentPage = 1;
		this.currentBlock = 1;
		this.rowSize = 10;
		this.pageSize = 5;
	}
	
	// 커스텀 페이징 설정
	public PagingUtil(int rowSize, int pageSize) {
		this.currentPage = 1;
		this.currentBlock = 1;
		this.rowSize = rowSize;
		this.pageSize = pageSize;
	}
	
	public void createPaging(int totalRow, int pageNo) {
		this.currentPage = pageNo >= 1 ? pageNo : 1;
		this.totalRow = totalRow >= 0 ? totalRow : 0;
		
		// 계산시작
		this.totalPage = this.totalRow > 0 && this.totalRow % this.rowSize == 0 ? this.totalRow / this.rowSize : this.totalRow / this.rowSize + 1;
		this.totalBlock = this.totalPage % this.pageSize == 0 ? this.totalPage / this.pageSize : this.totalPage / this.pageSize + 1;
		this.currentPage = this.currentPage > this.totalPage ? this.totalPage : this.currentPage; 		
		this.currentBlock = ((this.currentPage - 1) / this.pageSize) + 1;
		this.startRow = ((this.currentPage - 1) * this.rowSize);		
		this.prevPage = this.currentBlock > 1 ? (this.currentBlock - 1) * this.pageSize : 1;
		this.nextPage = this.currentBlock < this.totalBlock ? (this.currentBlock * this.pageSize) + 1 : this.totalPage;
		this.startPage = this.prevPage > 1 ? this.prevPage - 1 : 1;
		this.endPage = this.nextPage < this.totalPage ? this.nextPage - 1 : this.totalPage;
		this.firstPage = 1;
		this.lastPage = this.totalPage;
	}
}

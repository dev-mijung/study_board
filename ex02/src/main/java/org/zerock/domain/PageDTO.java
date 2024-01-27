package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;	// 전체 데이터 수
	private Criteria cri; 	// 현재 페이지 번호(pageNum), 페이지에서 보여주는 데이터 수 (amount) 들어있음
	
	public PageDTO(Criteria cri, int total) {
		
		this.cri = cri;
		this.total = total;
		
		// 끝페이지 번호 
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		
		// 시작페이지 번호 
		this.startPage = this.endPage - 9;
		
		
		int realEnd = (int)(Math.ceil((total * 1.0) / cri.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		
		this.next = this.endPage < realEnd;
	}
}

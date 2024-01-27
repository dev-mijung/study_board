package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

// Criteria 클래스의 용도는 pageNum과 amount 값을 같이 전달하는 용도
// 생성자를 통해서 기본값을 1페이지, 10개로 지정해서 처리
// lombok을 이용해서 getter/setter 생성

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	
	private int pageNum;	// 현재 페이지 번호 	
	private int amount;	// 페이지에서 보여주는 데이터 수
	
	private String type;
	private String keyword;
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	// 검색 조건이 각 글자(T,W,C)로 구성되어 있으므로 
	// 검색조건을 배열로 만들어서 한번에 처리하기 위함 
	// getTypeArr을 이용해서 MyBatis의 동적 태그를 활용할 수 있음
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
	
	// 웹페이지에서 매번 파라미터를 유지하는 일이 번거롭고 힘들다면
	// 한번쯤 UriComponentBuilder라는 클래스를 이용해 볼 필요가 있음
	// org.springframework.web.util.UriComponentBuilder는 여러 개의 파라미터들을 연결해서 URL의 형태로 만들어주는 기능을 가지고 있음
	// URL을 만들어주면 리다이렉트를 하거나 <form> 태그를 사용하는 상황을 많이 줄여줄 수 있음
	// 그래서 Criteria클래스에 링크를 생성하는 기능을 추가 함 
	public String getListLink() {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.amount)
				.queryParam("type", this.type)
				.queryParam("keyword", this.keyword);
		
		return builder.toUriString();
	}

}

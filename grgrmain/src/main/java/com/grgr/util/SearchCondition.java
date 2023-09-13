package com.grgr.util;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;
//작성자 : 김정현
//수정일-내용 : 0908 - 위치 정보를 검색 조건에 추가
@Data
public class SearchCondition {
	private Integer pageNum = 1;
	private Integer pageSize = 10;
	private Integer blockSize = 10;
	private String searchType ="";
	private String searchKeyword = "";
	private String keyword="";
	private String loginLocation="";
	
	public SearchCondition() {
		// TODO Auto-generated constructor stub
	}
	

	public SearchCondition(Integer pageNum, Integer pageSize, String searchType, String searchKeyword, String keyword) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.searchType = searchType;
		this.searchKeyword = searchKeyword;
		this.keyword = keyword;
	}

	
	//loginLocation 추가 생성자
	public SearchCondition(Integer pageNum, Integer pageSize, String searchType, String searchKeyword, String keyword,
			String loginLocation) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.searchType = searchType;
		this.searchKeyword = searchKeyword;
		this.keyword = keyword;
		this.loginLocation = loginLocation;
	}
	
	public String getQueryString(int pageNum) {
	    UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
	            .queryParam("pageNum", pageNum)
	            .queryParam("pageSize", pageSize);

	    if (searchType != null && !searchType.trim().isEmpty()) {
	        builder.queryParam("searchType", searchType);
	    }
	    if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
	        builder.queryParam("searchKeyword", searchKeyword);
	    }
	    if (keyword != null && !keyword.trim().isEmpty()) {
	        builder.queryParam("keyword", keyword);
	    }
		/* 위치정보가 쿼리스트링에 필요할 것인가 의문..
		 * if (loginLocation != null && !loginLocation.trim().isEmpty()) {
		 * builder.queryParam("loginLocation", loginLocation); }
		 */

	    return builder.build().toUriString();
	}
	
	public String getQueryString() {
		
		return getQueryString(pageNum);
		
	}























	
	

}

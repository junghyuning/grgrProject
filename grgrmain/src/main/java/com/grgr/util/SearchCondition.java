package com.grgr.util;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class SearchCondition {
	private Integer pageNum = 1;
	private Integer pageSize = 10;
	private Integer blockSize = 10;
	private String searchType ="";
	private String searchKeyword = "";
	private String keyword="";
	//private String location="";
	
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
//	    if (location != null && !location.trim().isEmpty()) {
//	    	builder.queryParam("location", location);
//	    }

	    return builder.build().toUriString();
	}
	
	public String getQueryString() {
		
		return getQueryString(pageNum);
		
	}





















	
	

}

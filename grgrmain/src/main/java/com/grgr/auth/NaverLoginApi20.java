package com.grgr.auth;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class NaverLoginApi20 extends DefaultApi20 implements NaverUrls{
//	싱글톤클래스로 생성
	private NaverLoginApi20() {
		
	}
	
	public static class InstanceHolder{
		private static final NaverLoginApi20 INSTANCE = new NaverLoginApi20();
	}
	
	 public static final NaverLoginApi20 instance() {
		 return InstanceHolder.INSTANCE; 
	 }
	
	
	@Override
	public String getAccessTokenEndpoint() {
		return NAVER_ACCESS_TOKEN;
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		// TODO Auto-generated method stub
		return NAVER_AUTH;
	}
	
	

}

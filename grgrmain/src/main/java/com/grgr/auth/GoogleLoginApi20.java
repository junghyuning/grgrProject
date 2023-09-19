package com.grgr.auth;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class GoogleLoginApi20 extends DefaultApi20 implements GoogleUrls{
//	싱글톤클래스로 생성
	private GoogleLoginApi20() {
		
	}
	
	public static class InstanceHolder{
		private static final GoogleLoginApi20 INSTANCE = new GoogleLoginApi20();
	}
	
	 public static final GoogleLoginApi20 instance() {
		 return InstanceHolder.INSTANCE; 
	 }
	
	
	@Override
	public String getAccessTokenEndpoint() {
		return GOOGLE_ACCESS_TOKEN;
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		// TODO Auto-generated method stub
		return GOOGLE_AUTH;
	}
	
	

}
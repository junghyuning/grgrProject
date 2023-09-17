package com.grgr.auth;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class KakaoLoginApi20 extends DefaultApi20 implements KakaoUrls{
//	싱글톤클래스로 생성
	private KakaoLoginApi20() {
		
	}
	
	public static class InstanceHolder{
		private static final KakaoLoginApi20 INSTANCE = new KakaoLoginApi20();
	}
	
	 public static final KakaoLoginApi20 instance() {
		 return InstanceHolder.INSTANCE; 
	 }
	
	
	@Override
	public String getAccessTokenEndpoint() {
		return KAKAO_ACCESS_TOKEN;
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		// TODO Auto-generated method stub
		return KAKAO_AUTH;
	}
	
	

}

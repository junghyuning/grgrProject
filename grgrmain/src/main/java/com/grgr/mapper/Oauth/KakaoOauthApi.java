package com.grgr.mapper.Oauth;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class KakaoOauthApi extends DefaultApi20{
	
	protected KakaoOauthApi() {
		
	}
	
	private static class InstanceHolder {
		private static final KakaoOauthApi INSTANCE = new KakaoOauthApi();
	}
	
	public static KakaoOauthApi instance() {
		return InstanceHolder.INSTANCE;
	}
	
	@Override
	public String getAccessTokenEndpoint() {
		return "https://kauth.kakao.com/oauth/token";
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		// TODO Auto-generated method stub
		return "https://kauth.kakao.com/oauth/authorize";
	}

}

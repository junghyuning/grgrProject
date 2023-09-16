package com.grgr.service.Oauth;

import javax.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import java.util.UUID;
 

 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 

public class KakaoLoginBO {
	private final static String KAKAO_CLIENT_ID = "1a1c60c327397c7ba897cc4205976cc0"; // REST API 키
	private final static String KAKAO_CLIENT_SECRET = "6NMkuMxVV3bR1Ogye8NaHhQixZrq0DXN"; // 클라이언트 시크릿 키
	private final static String KAKAO_REDIRECT_URI = "http://localhost:8000/user/login";
	private final static String SESSION_STATE = "kakao_oauth_state";
	private final static String PROFILE_API_URL = "https://kapi.kakao.com/v2/user/me";
	
	public String getAuthorizationURL(HttpSession session) {
		String state = generateRandomString();
	}
	
	private String generateRandomString() {
		return UUID.randomUUID().toString();
	}
	
	private void setSession(HttpSession session, String state) {
		session.setAttribute(SESSION_STATE, state);
	}
}

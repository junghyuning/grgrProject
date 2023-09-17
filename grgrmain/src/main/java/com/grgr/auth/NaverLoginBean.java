package com.grgr.auth;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.grgr.dto.UserVO;

import lombok.RequiredArgsConstructor;

@Component
public class NaverLoginBean implements NaverUrls {
	private static final String CLIENT_ID = "mHXwNpAULbrozZWrhDo2";
	private static final String CLIENT_SECRET = "totvPNJ11j";
	private static final String REDIRECT_URL = "http://localhost/grgrmain/oauth/naver/callback";
	private static final String SESSION_STATE = "naverState";

	private OAuth20Service oAuth20Service;

	public String getNaverAuthUrl(HttpSession session) {

		String state = UUID.randomUUID().toString();

		session.setAttribute(SESSION_STATE, state);

		// ScribeJava 에서 제공하는 인증 URL 생성 메서드
		oAuth20Service = new ServiceBuilder(CLIENT_ID).apiSecret(CLIENT_SECRET).callback(REDIRECT_URL).state(state)
				.build(NaverLoginApi20.instance());

		return oAuth20Service.getAuthorizationUrl();
	}

	// 로그인후 로그인 사용자의 접근 토큰을 발급받는 API를 호출하여 사용자 접근 토큰을 반환하기위한 메서드
	public OAuth2AccessToken getNaverAccessToken(HttpSession session, String code, String state)
			throws IOException, InterruptedException, ExecutionException {

		String sessionState = (String) session.getAttribute(SESSION_STATE);

		// 로그인 요청을 한 사용자와 토큰을 발급받는 사용자가 다른 경우
		if (!StringUtils.pathEquals(sessionState, state)) {
			return null;
		}
		oAuth20Service = new ServiceBuilder(CLIENT_ID).apiSecret(CLIENT_SECRET).callback(REDIRECT_URL).state(state)
				.build(NaverLoginApi20.instance());

		// scribe java에서 제공하는 AccessToken 획득기능
		OAuth2AccessToken accessToken = oAuth20Service.getAccessToken(code);
		return accessToken;
	}

	// 프로필 얻어오기 (프로필API 호출)
	public UserVO getUserProfile(OAuth2AccessToken accessToken)
			throws InterruptedException, ExecutionException, IOException, ParseException {
		oAuth20Service = new ServiceBuilder(CLIENT_ID).apiSecret(CLIENT_SECRET).callback(REDIRECT_URL)
				.build(NaverLoginApi20.instance());
		OAuthRequest request = new OAuthRequest(Verb.GET, NAVER_PROFILE_URL);
		oAuth20Service.signRequest(accessToken, request);
		Response response = oAuth20Service.execute(request);
		return parseJson(response.getBody());
	}

	// 파싱 및 테이블에 저장
	private UserVO parseJson(String profileReponse) throws ParseException {
		System.out.println("==============================================" + profileReponse
				+ "===============================================");
		UserVO userVO = new UserVO();

		// JSON문자열 -> 객체로변환
		JSONParser parser = new JSONParser();
		Object object = parser.parse(profileReponse);
		// object 객체로 파싱된 json문자열을 다시 JSON 객체로 저장
		JSONObject jsonObject = (JSONObject) object;

		// JSON 객체 -> 파싱
		JSONObject responseObject = (JSONObject) jsonObject.get("response");

		userVO.setUserName((String) responseObject.get("name"));
		//pw는 not null 조건이 걸려있으므로 랜덤값 생성하여 저장
		userVO.setUserPw(UUID.randomUUID().toString()); 
		userVO.setEmail((String) responseObject.get("email"));
		userVO.setPhone((String) responseObject.get("mobile"));
		userVO.setNickName((String) responseObject.get("nickname"));
		userVO.setNaverId((String) responseObject.get("id"));
	
		return userVO;
	}

}

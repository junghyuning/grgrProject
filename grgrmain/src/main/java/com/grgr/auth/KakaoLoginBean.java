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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.grgr.dto.UserVO;

@Component
public class KakaoLoginBean {
	private final static String KAKAO_CLIENT_ID = "1a1c60c327397c7ba897cc4205976cc0";
	private final static String KAKAO_CLIENT_SECRET = "mYsFx7f6cq869IvWU2NFBaqVzOuKusMw";
	private final static String KAKAO_REDIRECT_URI = "http://localhost:8000/oauth/kakao/callback";
	private final static String SESSION_STATE = "kakao_oauth_state";
	/* 프로필 조회 API URL */
	private final static String PROFILE_API_URL = "https://kapi.kakao.com/v2/user/me";

	/* 카카오로 인증 URL 생성 Method */
	public String getAuthorizationUrl(HttpSession session) {

		/* 세션 유효성 검증을 위하여 난수를 생성 */
		String state = generateRandomString();
		/* 생성한 난수 값을 session에 저장 */
		setSession(session, state);

		/* Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 카카오로 인증 URL 생성 */
		OAuth20Service oauthService = new ServiceBuilder().apiKey(KAKAO_CLIENT_ID).apiSecret(KAKAO_CLIENT_SECRET)
				.callback(KAKAO_REDIRECT_URI).state(state) // 앞서 생성한 난수값을 인증 URL생성시 사용함
				.build(KakaoLoginApi.instance());

		return oauthService.getAuthorizationUrl();
	}

	/* 카카오로 Callback 처리 및 AccessToken 획득 Method */
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException {
			
		/* Callback으로 전달받은 세선검증용 난수값과 세션에 저장되어있는 값이 일치하는지 확인 */
        String sessionState = getSession(session);
        if(StringUtils.pathEquals(sessionState, state)){

            OAuth20Service oauthService = new ServiceBuilder()
                    .apiKey(KAKAO_CLIENT_ID)
                    .apiSecret(KAKAO_CLIENT_SECRET)
                    .callback(KAKAO_REDIRECT_URI)
                    .state(state)
                    .build(KakaoLoginApi.instance());

            /* Scribe에서 제공하는 AccessToken 획득 기능으로 네아로 Access Token을 획득 */
            OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
            return accessToken;
        }
        return null;
    }

	/* 세션 유효성 검증을 위한 난수 생성기 */
	private String generateRandomString() {
		return UUID.randomUUID().toString();
	}

	/* http session에 데이터 저장 */
	private void setSession(HttpSession session, String state) {
		session.setAttribute(SESSION_STATE, state);
	}

	/* http session에서 데이터 가져오기 */
	private String getSession(HttpSession session) {
		return (String) session.getAttribute(SESSION_STATE);
	}

	/* 프로필 얻어오기 (프로필API 호출) */
	 public UserVO getUserProfile(OAuth2AccessToken oauthToken) throws IOException, ParseException{

	        OAuth20Service oauthService =new ServiceBuilder()
	                .apiKey(KAKAO_CLIENT_ID)
	                .apiSecret(KAKAO_CLIENT_SECRET)
	                .callback(KAKAO_REDIRECT_URI).build(KakaoLoginApi.instance());

	        OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
	        oauthService.signRequest(oauthToken, request);
	        Response response = request.send();

	        // 응답 데이터 확인
	        String responseBody = response.getBody();
	        System.out.println("Kakao API Response: " + responseBody);
	        
	        JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(responseBody);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();  
            
            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();
            
            UserVO userVO = new UserVO();
            userVO.setUserPw(UUID.randomUUID().toString());
            userVO.setEmail(email);
            userVO.setNickName(nickname);
            userVO.setUserName(nickname);
            userVO.setKakaoId(email);
            userVO.setUserId(email);
         
            System.out.println(userVO);
            return userVO;
	    }
}
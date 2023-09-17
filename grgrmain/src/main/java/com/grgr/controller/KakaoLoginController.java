package com.grgr.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.grgr.auth.KakaoLoginBean;
import com.grgr.dto.UserVO;
import com.grgr.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/oauth")
@RequiredArgsConstructor
@Slf4j
public class KakaoLoginController {
	private final KakaoLoginBean kakaoLoginBean;
	private final UserService userService;

	@RequestMapping("/kakao")
	public String kakaoLogin(HttpSession session) throws UnsupportedEncodingException {
		String kakaoAuthUrl = kakaoLoginBean.getKakaoAuthUrl(session);
		return "redirect:" + kakaoAuthUrl;
	}

	@RequestMapping("/kakao/callback")
	public String kakaoLoginCallback(@RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException, InterruptedException, ExecutionException, ParseException {
		// 1. 코드를 이용하여 엑세스 토큰 받기
		OAuth2AccessToken accessToken = kakaoLoginBean.getKakaoAccessToken(session, code, state);
		// 2. access Token 이용하여 사용자 프로필 정보 받아오기
		UserVO profile = kakaoLoginBean.getUserProfile(accessToken);
		log.info("profile : " + profile);
		// 3. DB에 해당 유저가 존재하는지 check (kakaoID 컬럼 추가) & userinfoDB에 삽입 or update
		if (userService.loginKakaoUser(profile) == false) {
			System.out.println("로그인 실패 오류");
			return "redirect:/user/login";
		}

		// 4.세션에 로그인 정보 저장 - 정상적으로 작동했다면 KakaoId 가 존재할 것임..
		UserVO user = userService.getKakaoLoginUser(profile.getKakaoId());
		session.setAttribute("loginId", user.getUserId());
		session.setAttribute("loginNickname", user.getNickName());
		session.setAttribute("loginUno", user.getUno());
		session.setAttribute("loginActive", user.getActive());
		session.setAttribute("loginUserStatus", user.getUserStatus());
		session.setAttribute("loginLastLogin", user.getLastLogin());

		return "redirect:/main";

	}

}

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
import com.grgr.auth.GoogleLoginBean;
import com.grgr.dto.UserVO;
import com.grgr.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/oauth")
@RequiredArgsConstructor
@Slf4j
public class GoogleLoginController {
	private final GoogleLoginBean googleLoginBean;
	private final UserService userService;

	@RequestMapping("/google")
	public String googleLogin(HttpSession session) throws UnsupportedEncodingException {
		String googleAuthUrl = googleLoginBean.getgoogleAuthUrl(session);
		return "redirect:" + googleAuthUrl;
	}

	@RequestMapping("/google/callback")
	public String googleLoginCallback(@RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException, InterruptedException, ExecutionException, ParseException {
		// 1. 코드를 이용하여 엑세스 토큰 받기
		OAuth2AccessToken accessToken = googleLoginBean.getgoogleAccessToken(session, code, state);
		// 2. access Token 이용하여 사용자 프로필 정보 받아오기
		UserVO profile = googleLoginBean.getUserProfile(accessToken);
		log.info("profile : " + profile);
		// 3. DB에 해당 유저가 존재하는지 check (googleID 컬럼 추가) & userinfoDB에 삽입 or update
		if (userService.loginGoogleUser(profile) == false) {
			System.out.println("로그인 실패 오류");
			return "redirect:/user/login";
		}

		// 4.세션에 로그인 정보 저장 
		UserVO user = userService.getGoogleLoginUser(profile.getGoogleId());
		session.setAttribute("loginId", user.getUserId());
		session.setAttribute("loginNickname", user.getNickName());
		session.setAttribute("loginUno", user.getUno());
		session.setAttribute("loginActive", user.getActive());
		session.setAttribute("loginUserStatus", user.getUserStatus());
		session.setAttribute("loginLastLogin", user.getLastLogin());
		session.setAttribute("loginLocation", user.getUserLoc());

		return "redirect:/main";

	}

}
package com.grgr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grgr.dto.UserVO;
import com.grgr.service.OAuthService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthService oAuthService;

    @GetMapping("/google")
    public String redirectToGoogleOAuth() {
        // 구글 OAuth 인증 시작 및 리다이렉트
        String googleAuthUrl = oAuthService.getGoogleAuthorizationUrl();
        return "redirect:" + googleAuthUrl;
    }

    @GetMapping("/google/callback")
    public String handleGoogleOAuthCallback(@RequestParam("code") String code, HttpSession session) {
        // 구글 OAuth 콜백 처리
        UserVO userInfo = oAuthService.getUserInfoFromGoogle(code);

        if (userInfo != null) {
            // userInfo를 사용하여 로그인 또는 회원가입 처리
            // 사용자 정보를 데이터베이스에 추가 또는 업데이트
            // 세션에 사용자 정보 저장 또는 처리
            
            return "redirect:/main"; // OAuth 로그인 이후에 리다이렉트할 페이지
        } else {
            // OAuth 로그인 실패 처리
            return "redirect:/login"; // 실패 시 로그인 페이지로 리다이렉트
        }
    }
}

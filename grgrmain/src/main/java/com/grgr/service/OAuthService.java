package com.grgr.service;

import com.grgr.dto.UserVO;

public interface OAuthService {
    String getGoogleAuthorizationUrl();
    UserVO getUserInfoFromGoogle(String code);
}
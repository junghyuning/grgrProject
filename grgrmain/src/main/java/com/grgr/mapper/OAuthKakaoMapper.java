package com.grgr.mapper;

import com.grgr.dto.UserVO;

public interface OAuthKakaoMapper {
	UserVO selectByEmail(String email);
	int insertSnsUser(UserVO userVO);
	int updateSnsUser(UserVO userVO);
	UserVO selectByKakaoId(String kakaoId);
}

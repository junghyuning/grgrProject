package com.grgr.mapper;

import com.grgr.dto.UserVO;

public interface OAuthGoogleMapper {
	UserVO selectByEmail(String email);
	int insertSnsUser(UserVO userVO);
	int updateSnsUser(UserVO userVO);
	UserVO selectByGoogleId(String GoogleId);
}

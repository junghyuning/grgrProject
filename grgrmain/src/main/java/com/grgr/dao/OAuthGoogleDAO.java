package com.grgr.dao;

import java.util.Map;

import com.grgr.dto.UserVO;

public interface OAuthGoogleDAO {
	UserVO selectByEmail(String email);
	int insertSnsUser(UserVO userVO);
	int updateSnsUser(UserVO userVO);
	UserVO selectByGoogleId(String googleId);
}

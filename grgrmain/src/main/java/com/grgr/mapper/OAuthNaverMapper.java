package com.grgr.mapper;

import com.grgr.dto.UserVO;

public interface OAuthNaverMapper {
	UserVO selectByEmail(String email);
	int insertSnsUser(UserVO userVO);
	int updateSnsUser(UserVO userVO);
	UserVO selectByNaverId(String naverId);
}

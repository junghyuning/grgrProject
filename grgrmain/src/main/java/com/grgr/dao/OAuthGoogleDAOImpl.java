package com.grgr.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.UserVO;
import com.grgr.mapper.OAuthGoogleMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OAuthGoogleDAOImpl implements OAuthGoogleDAO {
	private final SqlSession session;

	@Override
	public UserVO selectByEmail(String email) {
		// TODO Auto-generated method stub
		return session.getMapper(OAuthGoogleMapper.class).selectByEmail(email);
	}

	@Override
	public int insertSnsUser(UserVO userVO) {
		// TODO Auto-generated method stub
		return session.getMapper(OAuthGoogleMapper.class).insertSnsUser(userVO);
	}

	@Override
	public int updateSnsUser(UserVO userVO) {
		// TODO Auto-generated method stub
		return session.getMapper(OAuthGoogleMapper.class).updateSnsUser(userVO);
	}

	@Override
	public UserVO selectByGoogleId(String GoogleId) {
		// TODO Auto-generated method stub
		return session.getMapper(OAuthGoogleMapper.class).selectByGoogleId(GoogleId);
	}
	
}
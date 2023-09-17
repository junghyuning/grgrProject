package com.grgr.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.UserVO;
import com.grgr.mapper.OAuthNaverMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OAuthNaverDAOImpl implements OAuthNaverDAO {
	private final SqlSession session;

	@Override
	public UserVO selectByEmail(String email) {
		// TODO Auto-generated method stub
		return session.getMapper(OAuthNaverMapper.class).selectByEmail(email);
	}

	@Override
	public int insertSnsUser(UserVO userVO) {
		// TODO Auto-generated method stub
		return session.getMapper(OAuthNaverMapper.class).insertSnsUser(userVO);
	}

	@Override
	public int updateSnsUser(UserVO userVO) {
		// TODO Auto-generated method stub
		return session.getMapper(OAuthNaverMapper.class).updateSnsUser(userVO);
	}

	@Override
	public UserVO selectByNaverId(String naverId) {
		// TODO Auto-generated method stub
		return session.getMapper(OAuthNaverMapper.class).selectByNaverId(naverId);
	}
	
}

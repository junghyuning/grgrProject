package com.grgr.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.UserVO;
import com.grgr.mapper.OAuthKakaoMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OAuthKakaoDAOImpl implements OAuthKakaoDAO {
	private final SqlSession session;

	@Override
	public UserVO selectByEmail(String email) {
		// TODO Auto-generated method stub
		return session.getMapper(OAuthKakaoMapper.class).selectByEmail(email);
	}

	@Override
	public int insertSnsUser(UserVO userVO) {
		// TODO Auto-generated method stub
		return session.getMapper(OAuthKakaoMapper.class).insertSnsUser(userVO);
	}

	@Override
	public int updateSnsUser(UserVO userVO) {
		// TODO Auto-generated method stub
		return session.getMapper(OAuthKakaoMapper.class).updateSnsUser(userVO);
	}

	@Override
	public UserVO selectByKakaoId(String kakaoId) {
		// TODO Auto-generated method stub
		return session.getMapper(OAuthKakaoMapper.class).selectByKakaoId(kakaoId);
	}

	@Override
	public int updateKakaoUser(UserVO userVO) {
		// TODO Auto-generated method stub
		return session.getMapper(OAuthKakaoMapper.class).updateKakaoUser(userVO);
	}

}

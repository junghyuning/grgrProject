package com.grgr.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.mapper.FreeLikeMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class FreeLikeDAOImpl implements FreeLikeDAO{
	private final SqlSession session;
	@Override
	public int insertMyFreeLike(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return session.getMapper(FreeLikeMapper.class).insertMyFreeLike(map);
	}

	@Override
	public int deleteMyFreeLike(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return session.getMapper(FreeLikeMapper.class).deleteMyFreeLike(map);
	}

	@Override
	public int getMyLikeStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return session.getMapper(FreeLikeMapper.class).getMyLikeStatus(map);
	}

	@Override
	public int FreeLikeCnt(int FreeBno) {
		// TODO Auto-generated method stub
		return session.getMapper(FreeLikeMapper.class).FreeLikeCnt(FreeBno);
	}
	
}

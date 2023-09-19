package com.grgr.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.FreeBoard;
import com.grgr.dto.MainInfo;
import com.grgr.dto.MainProduct;
import com.grgr.dto.ProductBoardVO;
import com.grgr.mapper.MainPageMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MainPageDAOImpl implements MainPageDAO{
	private final SqlSession sqlSession;
	
	@Override
	public List<MainInfo> selectNewInfo() {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MainPageMapper.class).selectNewInfo();
	}

	@Override
	public List<FreeBoard> selectNewFree() {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MainPageMapper.class).selectNewFree();
	}

	@Override
	public List<MainProduct> selectNewSales() {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MainPageMapper.class).selectNewSales();
	}

}

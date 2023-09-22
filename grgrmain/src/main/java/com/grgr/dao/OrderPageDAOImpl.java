package com.grgr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.OrderPage;
import com.grgr.dto.ProductBoardVO;
import com.grgr.dto.ProductCartDTO;
import com.grgr.mapper.OrderPageMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderPageDAOImpl implements OrderPageDAO {
	private final SqlSession sqlSession;
	
	@Override
	public List<ProductCartDTO> selectCartOrderPage(Map<String, Object> map) {
		return sqlSession.getMapper(OrderPageMapper.class).selectCartOrderPage(map); 
	}

	@Override
	public List<ProductBoardVO> selectProductOrderPage(Map<String, Object> map) {
		return sqlSession.getMapper(OrderPageMapper.class).selectProductOrderPage(map);
	}

	@Override
	public int insertOrderPage(OrderPage orderPage) {
		return sqlSession.getMapper(OrderPageMapper.class).insertOrderPage(orderPage);
	}

	@Override
	public List<ProductBoardVO> selectOrderUserinfo(Map<String, Object> map) {
		return sqlSession.getMapper(OrderPageMapper.class).selectOrderUserinfo(map);
	}
	
}

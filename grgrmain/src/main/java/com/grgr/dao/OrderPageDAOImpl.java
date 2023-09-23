package com.grgr.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.OrderPage;
import com.grgr.dto.ProductBoardVO;
import com.grgr.dto.ProductCartDTO;
import com.grgr.dto.Userinfo;
import com.grgr.mapper.OrderPageMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderPageDAOImpl implements OrderPageDAO {
	private final SqlSession sqlSession;
	
	@Override
	public ProductCartDTO selectCartOrderPage(int productCartNo) {
		return sqlSession.getMapper(OrderPageMapper.class).selectCartOrderPage(productCartNo); 
	}

	@Override
	public ProductBoardVO selectProductOrderPage(int productId) {
		return sqlSession.getMapper(OrderPageMapper.class).selectProductOrderPage(productId);
	}

	@Override
	public int insertOrderPage(OrderPage orderPage) {
		return sqlSession.getMapper(OrderPageMapper.class).insertOrderPage(orderPage);
	}

	@Override
	public Userinfo selectOrderUserinfo(int uno) {
		return sqlSession.getMapper(OrderPageMapper.class).selectOrderUserinfo(uno);
	}
	
}

package com.grgr.dao;

import java.util.List;
import java.util.Map;

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
	public int selectLastOrderGroup() {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(OrderPageMapper.class).selectLastOrderGroup();
	}
	
	@Override
	public int insertOrderPage(OrderPage orderPage) {
		return sqlSession.getMapper(OrderPageMapper.class).insertOrderPage(orderPage);
	}
	
	@Override
	public Userinfo selectOrderUserinfo(int uno) {
		return sqlSession.getMapper(OrderPageMapper.class).selectOrderUserinfo(uno);
	}
	
	@Override
	public ProductCartDTO selectCartByCartNo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(OrderPageMapper.class).selectCartByCartNo(map);
	}
	
	@Override
	public List<ProductCartDTO> selectCartOrderPage(Map<String, Object> map) {
		return sqlSession.getMapper(OrderPageMapper.class).selectCartOrderPage(map); 
	}

	@Override
	public ProductBoardVO selectProductOrderPage(Map<String, Object> map) {
		return sqlSession.getMapper(OrderPageMapper.class).selectProductOrderPage(map);
	}








	
}

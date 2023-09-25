package com.grgr.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.MyOrderListDTO;
import com.grgr.mapper.MyOrderListMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyOrderListDAOImpl implements MyOrderListDAO{
	private final SqlSession sqlSession;

	@Override
	public List<MyOrderListDTO> selectMyOrderList(int uno) {
		return sqlSession.getMapper(MyOrderListMapper.class).selectMyOrderList(uno);
	}
	
}

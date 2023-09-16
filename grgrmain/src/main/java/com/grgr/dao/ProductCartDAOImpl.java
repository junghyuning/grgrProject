package com.grgr.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.ProductCartDTO;
import com.grgr.mapper.ProductCartMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductCartDAOImpl implements ProductCartDAO {
	private final SqlSession sqlSession;

	@Override
	public int insertCart(ProductCartDTO cart) {
		return sqlSession.getMapper(ProductCartMapper.class).insertCart(cart);
	}

	@Override
	public int deleteCart(int productCartNo) {
		return sqlSession.getMapper(ProductCartMapper.class).deleteCart(productCartNo);
	}

	@Override
	public int updateCart(ProductCartDTO cart) {
		return sqlSession.getMapper(ProductCartMapper.class).updateCart(cart);
	}

	@Override
	public List<ProductCartDTO> selectCartList(int uno) {
		return sqlSession.getMapper(ProductCartMapper.class).selectCartList(uno);
	}

	@Override
	public ProductCartDTO selectCart(ProductCartDTO cart) {
		return sqlSession.getMapper(ProductCartMapper.class).selectCart(cart);
	}

}

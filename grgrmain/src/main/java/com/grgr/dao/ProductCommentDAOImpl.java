package com.grgr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.ProductComment;
import com.grgr.mapper.ProductCommentMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductCommentDAOImpl implements ProductCommentDAO {
	private final SqlSession sqlSession;

	@Override
	public int productCommentCount(int productId) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ProductCommentMapper.class).productCommentCount(productId);
	}

	@Override
	public List<ProductComment> selectProductCommentList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ProductCommentMapper.class).selectProductCommentList(map);
	}

	@Override
	public int insertProductComment(ProductComment productComment) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ProductCommentMapper.class).insertProductComment(productComment);
	}

	@Override
	public int updateProductComment(ProductComment productComment) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ProductCommentMapper.class).updateProductComment(productComment);
	}

	@Override
	public int deleteProductComment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ProductCommentMapper.class).deleteProductComment(map);
	}

	@Override
	public int blindProductComment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ProductCommentMapper.class).blindProductComment(map);
	}


}

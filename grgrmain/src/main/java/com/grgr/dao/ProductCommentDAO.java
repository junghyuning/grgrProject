package com.grgr.dao;

import java.util.List;
import java.util.Map;

import com.grgr.dto.ProductComment;

public interface ProductCommentDAO {
	int productCommentCount(int productId);

	List<ProductComment> selectProductCommentList(Map<String, Object> map);

	int insertProductComment(ProductComment productComment);

	int updateProductComment(ProductComment productComment);

	int deleteProductComment(Map<String, Object> map);

	int blindProductComment(Map<String, Object> map);

}

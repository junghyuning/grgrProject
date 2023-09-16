package com.grgr.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.grgr.dao.ProductCommentDAO;
import com.grgr.dto.ProductComment;
import com.grgr.exception.CommentBlindException;
import com.grgr.exception.CommentInsertException;
import com.grgr.exception.CommentModifyException;
import com.grgr.exception.CommentRemoveException;
import com.grgr.util.CommentPager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCommentServiceImpl implements ProductCommentService {
	private final ProductCommentDAO productCommentDAO;

	@Override
	public void getProductCommentCount(int productId) {
		// TODO Auto-generated method stub
		productCommentDAO.productCommentCount(productId);

	}

	@Override
	public Map<String, Object> getProductCommentList(int productId, int pageNum) {
		int totalComment = productCommentDAO.productCommentCount(productId);
		CommentPager commentPager = new CommentPager(pageNum, totalComment);
		Map<String, Object> map = new HashMap<>();
		map.put("productId", productId);
		map.put("startRow", commentPager.getStartRow());
		map.put("endRow", commentPager.getEndRow());
		List<ProductComment> productCommentList = productCommentDAO.selectProductCommentList(map);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("productCommentList", productCommentList);
		resultMap.put("commentPager", commentPager);
		return resultMap;
	}

	@Override
	public void addProductComment(ProductComment productComment) throws CommentInsertException {
		// TODO Auto-generated method stub
		int result = productCommentDAO.insertProductComment(productComment);
		if (result != 1) {
			throw new CommentInsertException("댓글 등록에 실패하였습니다.");
		}
	}

	@Override
	public void modifyProductComment(ProductComment productComment) throws CommentModifyException {
		// TODO Auto-generated method stub
		int result = productCommentDAO.updateProductComment(productComment);
		if (result != 1) {
			throw new CommentModifyException("댓글 수정에 실패하였습니다.");
		}

	}

	@Override
	public void removeProductComment(int productCommentNo, int uno) throws CommentRemoveException {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("productCommentNo", productCommentNo);
		map.put("uno", uno);
		int result = productCommentDAO.deleteProductComment(map);
		if (result != 1) {
			throw new CommentRemoveException("댓글 삭제에 실패하였습니다");
		}

	}

	@Override
	public void hideProductComment(int uno, int productCommentNo, int userStatus) throws CommentBlindException {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("productCommentNo", productCommentNo);
		map.put("uno", uno);
		map.put("userStatus", userStatus);
		int result = productCommentDAO.blindProductComment(map);
		if (result != 1) {
			throw new CommentBlindException("댓글 숨김에 실패하였습니다");
		}

	}


}

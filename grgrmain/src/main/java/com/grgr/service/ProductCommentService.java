package com.grgr.service;

import java.util.Map;

import com.grgr.dto.ProductComment;
import com.grgr.exception.CommentBlindException;
import com.grgr.exception.CommentInsertException;
import com.grgr.exception.CommentModifyException;
import com.grgr.exception.CommentRemoveException;

public interface ProductCommentService {
	// 게시글 내 전체 댓글수(페이징)
	public void getProductCommentCount(int productId);

	// 댓글 출력(리스트)
	public Map<String, Object> getProductCommentList(int productId, int pageNum);

	// 댓글 입력
	public void addProductComment(ProductComment productComment) throws CommentInsertException;

	// 댓글 수정
	public void modifyProductComment(ProductComment productComment) throws CommentModifyException;

	// 댓글 삭제
	public void removeProductComment(int productCommentNo, int uno) throws CommentRemoveException;

	// 댓글 숨김(관리자)
	public void hideProductComment(int uno, int productCommentNo, int userStatus) throws CommentBlindException;

}

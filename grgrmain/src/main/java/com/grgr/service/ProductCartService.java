package com.grgr.service;

import java.util.List;

import com.grgr.dto.ProductCartDTO;

public interface ProductCartService {
	/* 장바구니에 추가 */
	int addCart(ProductCartDTO cart) throws Exception;

	/* 장바구니에서 삭제 */
	int removeCart(int productCartNo);

	/* 장바구니 수량 수정 */
	int modifyCart(ProductCartDTO cart);

	/* 장바구니 목록 조회 */
	List<ProductCartDTO> getCartList(int uno);

	/* 장바구니 확인 */
	ProductCartDTO getCart(ProductCartDTO cart);
}

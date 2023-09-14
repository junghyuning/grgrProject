package com.grgr.mapper;

import java.util.List;

import com.grgr.dto.ProductCartDTO;

public interface ProductCartMapper {
	/* 장바구니에 추가 */
	int insertCart(ProductCartDTO cart);

	/* 장바구니에서 삭제 */
	int deleteCart(int productCartNo);

	/* 장바구니 수량 수정 */
	int updateCart(ProductCartDTO cart);

	/* 장바구니 목록 조회 */
	List<ProductCartDTO> selectCartList(int uno);

	/* 장바구니 확인 */
	ProductCartDTO selectCart(ProductCartDTO cart);

}

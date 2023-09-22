package com.grgr.dao;

import java.util.List;
import java.util.Map;

import com.grgr.dto.OrderPage;
import com.grgr.dto.ProductBoardVO;
import com.grgr.dto.ProductCartDTO;

public interface OrderPageDAO {
	List<ProductCartDTO> selectCartOrderPage(Map<String, Object> map);//장바구니 목록
	List<ProductBoardVO> selectProductOrderPage(Map<String, Object> map);//바로구매 목록
	int insertOrderPage(OrderPage orderPage);//주문페이지 삽입
	List<ProductBoardVO> selectOrderUserinfo(Map<String, Object> map);//유저정보
}

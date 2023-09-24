package com.grgr.mapper;

import java.util.List;
import java.util.Map;

import com.grgr.dto.OrderPage;
import com.grgr.dto.ProductBoardVO;
import com.grgr.dto.ProductCartDTO;
import com.grgr.dto.Userinfo;

public interface OrderPageMapper {
	int selectLastOrderGroup();
	int insertOrderPage(OrderPage orderPage);
	Userinfo selectOrderUserinfo(int uno);//유저정보
	ProductCartDTO selectCartByCartNo(Map<String, Object> map); //카트넘버 + 사용자 번호
	
	
	List<ProductCartDTO> selectCartOrderPage(Map<String, Object> map);//장바구니 목록
	ProductBoardVO selectProductOrderPage(Map<String, Object> map);//바로구매 목록
}

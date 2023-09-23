package com.grgr.mapper;

import java.util.List;
import java.util.Map;

import com.grgr.dto.OrderPage;
import com.grgr.dto.ProductBoardVO;
import com.grgr.dto.ProductCartDTO;
import com.grgr.dto.Userinfo;

public interface OrderPageMapper {
	List<ProductCartDTO> selectCartOrderPage(Map<String, Object> map);//장바구니 목록
	ProductBoardVO selectProductOrderPage(Map<String, Object> map);//바로구매 목록
	int insertOrderPage(OrderPage orderPage);//주문내역 삽입
	Userinfo selectOrderUserinfo(int uno);//유저정보
}

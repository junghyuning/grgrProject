package com.grgr.service;

import java.util.Map;

import com.grgr.dto.OrderPage;

public interface OrderPageService {
	Map<String, Object> getCartOrderPage(int loginUno, Integer productId);//장바구니 목록
	Map<String, Object> getProductOrderPage(int loginUno, Integer productId);//바로구매 목록
	int addOrderPage(OrderPage orderPage);//주문페이지 삽입
}

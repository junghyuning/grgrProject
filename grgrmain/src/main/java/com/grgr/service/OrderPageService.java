package com.grgr.service;

import java.util.List;
import java.util.Map;

import com.grgr.dto.OrderPage;
import com.grgr.exception.CartDeleteFailException;
import com.grgr.exception.CartNullException;
import com.grgr.exception.OrderInsertFailException;

public interface OrderPageService {
	void addOrderedItems(List<Integer> selectedItemList, int loginUno)
			throws CartNullException, CartDeleteFailException, OrderInsertFailException;
	Map<String, Object> getCartOrderPage(int loginUno, int productCartNo);//장바구니 목록
	Map<String, Object> getProductOrderPage(int loginUno, int productId);//바로구매 목록
	int addOrderPage(OrderPage orderPage);//주문페이지 삽입
}
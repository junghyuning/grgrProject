package com.grgr.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.grgr.dto.OrderPage;
import com.grgr.dto.ProductBoardVO;
import com.grgr.exception.CartDeleteFailException;
import com.grgr.exception.CartNullException;
import com.grgr.exception.OrderInsertFailException;

public interface OrderPageService {
	void addOrderedItems(List<Integer> selectedItemList, int loginUno,HttpSession session)
			throws CartNullException, CartDeleteFailException, OrderInsertFailException;

	void addDirectPurchase(OrderPage orderPage) throws OrderInsertFailException;
	Map<String, Object> getOrderInfo(int loginUno);

	OrderPage getOrderInfo(String orderGroup);
}
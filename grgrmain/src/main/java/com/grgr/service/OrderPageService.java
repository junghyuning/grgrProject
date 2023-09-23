package com.grgr.service;

import java.util.List;

import com.grgr.dto.OrderPage;
import com.grgr.dto.ProductBoardVO;
import com.grgr.dto.ProductCartDTO;
import com.grgr.dto.Userinfo;

public interface OrderPageService {
	ProductCartDTO getCartOrderPage(int loginUno, int productCartNo, List<Integer> cartOrderList);//장바구니 목록
	ProductBoardVO getProductOrderPage(int loginUno, int productId);//바로구매 목록
	int addOrderPage(OrderPage orderPage);//주문페이지 삽입
	Userinfo selectOrderUserinfo(int uno);//유저정보
}

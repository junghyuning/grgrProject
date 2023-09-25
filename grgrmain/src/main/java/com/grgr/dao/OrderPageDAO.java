package com.grgr.dao;

import java.util.List;
import java.util.Map;

import com.grgr.dto.OrderListDTO;
import com.grgr.dto.OrderPage;
import com.grgr.dto.ProductBoardVO;
import com.grgr.dto.ProductCartDTO;
import com.grgr.dto.Userinfo;

public interface OrderPageDAO {
	int selectLastOrderGroup();
	int insertOrderPage(OrderPage orderPage);
	Userinfo selectOrderUserinfo(int uno);//유저정보
	List<OrderListDTO> selectRecentOrderListByUno(int uno);
	ProductCartDTO selectCartByCartNo(Map<String, Object> map);//카트에서 정보 받아오기
	OrderPage getOrderInfo(String orderGroup);
	
	
//	List<ProductCartDTO> selectCartOrderPage(Map<String, Object> map);//장바구니 목록
//	ProductBoardVO selectProductOrderPage(Map<String, Object> map);//바로구매 목록

}
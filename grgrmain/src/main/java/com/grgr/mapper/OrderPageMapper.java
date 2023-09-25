package com.grgr.mapper;

import java.util.List;
import java.util.Map;

import com.grgr.dto.OrderListDTO;
import com.grgr.dto.OrderPage;
import com.grgr.dto.ProductCartDTO;
import com.grgr.dto.Userinfo;

public interface OrderPageMapper {
	int selectLastOrderGroup();
	int insertOrderPage(OrderPage orderPage);
	Userinfo selectOrderUserinfo(int uno);//유저정보
	List<OrderListDTO> selectRecentOrderListByUno(int uno);
	ProductCartDTO selectCartByCartNo(Map<String, Object> map); //카트넘버 + 사용자 번호
	OrderPage getOrderInfo(String orderNo);

}
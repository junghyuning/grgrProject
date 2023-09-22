package com.grgr.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grgr.dao.OrderPageDAO;
import com.grgr.dto.OrderPage;
import com.grgr.dto.ProductBoardVO;
import com.grgr.dto.ProductCartDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderPageServiceImpl implements OrderPageService {
	private final OrderPageDAO orderPageDAO;

	@Override
	public Map<String, Object> getCartOrderPage(int loginUno, Integer productId) {
		//장바구니 출력
		Map<String, Object> cartMap = new HashMap<String, Object>();

		//매개변수 전달
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("loginUno", loginUno);
		resultMap.put("productId", productId);

		//DAO를 통해 장바구니 목록을 조회
		List<ProductCartDTO> cartList = orderPageDAO.selectCartOrderPage(resultMap);
		cartMap.put("cartList", cartList);

		return cartMap;
	}

	@Override
	public Map<String, Object> getProductOrderPage(int loginUno, Integer productId) {
		//바로구매 출력
		Map<String, Object> productMap = new HashMap<String, Object>();

		//매개변수 전달
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("loginUno", loginUno);
		resultMap.put("productId", productId);

		//DAO를 통해 바로구매 조회
		List<ProductBoardVO> product = orderPageDAO.selectProductOrderPage(resultMap);
		productMap.put("product", product);

		return productMap;
	}

	@Override
	@Transactional
	public int addOrderPage(OrderPage orderPage) {
		orderPageDAO.insertOrderPage(orderPage);
		
		return orderPage.getOrderNo();
	}
}

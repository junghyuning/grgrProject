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
import com.grgr.dto.Userinfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderPageServiceImpl implements OrderPageService {
	private final OrderPageDAO orderPageDAO;

	@Override
	public Map<String, Object> getCartOrderPage(int loginUno, int productCartNo) {
		Map<String, Object> cartMap = new HashMap<String, Object>();
		cartMap.put("uno", loginUno);
		cartMap.put("productCartNo", productCartNo);

		// 장바구니 목록 출력
		List<ProductCartDTO> cartList = orderPageDAO.selectCartOrderPage(cartMap);
		log.info("cartList" + cartList);

		// 유저 조회
		Userinfo userInfo = orderPageDAO.selectOrderUserinfo(loginUno);
		log.info("userInfo" + userInfo);

		// cart 테이블 where절 join
		for (ProductCartDTO cart : cartList) {
			if (cartList != null && !cartList.isEmpty()) {
				cart.setUno(loginUno);
				orderPageDAO.selectCartOrderPage(cartMap);
			}
		}
		return cartMap;
	}

	@Override
	public Map<String, Object> getProductOrderPage(int loginUno, int productId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uno", loginUno);
		map.put("productId", productId);

		//바로구매 출력
		ProductBoardVO product = orderPageDAO.selectProductOrderPage(map);
		log.info("product" + product);

		// 유저 조회
		Userinfo userInfo = orderPageDAO.selectOrderUserinfo(loginUno);
		log.info("userInfo" + userInfo);

		// 주문테이블에 삽입
		if (product != null) {
			product.setUno(loginUno);
			orderPageDAO.selectProductOrderPage(map);
		}
		return map;
	}

	@Override
	@Transactional
	public int addOrderPage(OrderPage orderPage) {
		orderPageDAO.insertOrderPage(orderPage);

		return orderPage.getOrderNo();
	}
}

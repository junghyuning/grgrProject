package com.grgr.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.FetchProfile.Item;

import org.junit.internal.runners.statements.Fail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grgr.dao.OrderPageDAO;
import com.grgr.dao.ProductCartDAO;
import com.grgr.dto.OrderPage;
import com.grgr.dto.ProductBoardVO;
import com.grgr.dto.ProductCartDTO;
import com.grgr.dto.Userinfo;
import com.grgr.exception.CartDeleteFailException;
import com.grgr.exception.CartNullException;
import com.grgr.exception.OrderInsertFailException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderPageServiceImpl implements OrderPageService {
	private final OrderPageDAO orderPageDAO;
	private final ProductCartDAO productCartDAO;

	// 장바구니 목록을 주문테이블에 저장 - 주문테이블에 저장한 장바구니 목록은 삭제
	@Transactional
	@Override
	public void addOrderedItems(List<Integer> selectedItemList, int loginUno)
			throws CartNullException, CartDeleteFailException, OrderInsertFailException {
		int orderGroup = orderPageDAO.selectLastOrderGroup() + 1;

		if (selectedItemList == null) {
			throw new CartNullException("장바구니에 상품이 존재하지 않습니다.");
		}

		for (int cartNo : selectedItemList) {
			Map<String, Object> map = new HashMap<>();
			map.put("productCartNo", cartNo);
			map.put("uno", loginUno);
			ProductCartDTO cartDTO = orderPageDAO.selectCartByCartNo(map);
			if (cartDTO == null) {
				throw new CartNullException("회원님의 장바구니에 존재하지 않는 상품입니다.");
			}
			OrderPage orderPage = new OrderPage();
			orderPage.setOrderGroup(orderGroup);
			orderPage.setProductId(cartDTO.getProductId());
			orderPage.setUno(cartDTO.getUno());
			orderPage.setOrderQuantity(cartDTO.getProductCount());

			int result = orderPageDAO.insertOrderPage(orderPage);
			if (result < 1) {
				throw new OrderInsertFailException("주문목록에 담는 과정에 오류가 발생하였습니다.");
			}

			result = productCartDAO.deleteCart(cartNo);

			if (result < 1) {
				throw new CartDeleteFailException("장바구니 삭제시에 예상치못한 오류가 발생하였습니다.");
			}
		}
	}

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

		// 바로구매 출력
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
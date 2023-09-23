package com.grgr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grgr.dto.OrderPage;
import com.grgr.dto.ProductCartDTO;
import com.grgr.service.OrderPageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderPageController {
	private final OrderPageService orderPageService;

	// 장바구니에서 넘어오는 데이터 받아서 출력 및 주문내역에 추가하는 메서드
	@RequestMapping("/cart/{productCartNo}")
	public String cartOrderPage(@RequestBody List<Integer> selectedItemList, HttpSession session,
			Model model) throws NumberFormatException {

		
		
		return "board/orderpage";
	}

	Map<String, Object> result = orderPageService.getCartOrderPage(loginUno,
			productCartNoList);log.info("result"+result);

	model.addAttribute("cartOrderPage",result.get("cartOrderPage"));

	return"board/orderpage";
	}

	// 바로구매
	@RequestMapping("/product/{productId}")
	public String porductOrderPage(@PathVariable Integer productId, HttpSession session, Model model) {
		log.info("@@@@@ OrderPageController 클래스의 porductOrderPage 호출");

		Integer loginUno = (Integer) session.getAttribute("loginUno");

		Map<String, Object> result = orderPageService.getProductOrderPage(loginUno, productId);

		model.addAttribute("product", result.get("product"));

		return "board/orderpage";
	}
}

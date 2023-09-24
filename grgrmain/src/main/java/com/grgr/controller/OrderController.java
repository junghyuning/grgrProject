package com.grgr.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grgr.dto.OrderPage;
import com.grgr.service.OrderPageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
	private final OrderPageService orderPageService;
	
	// 장바구니 목록을 출력하는 컨트롤러
	@RequestMapping("/cart")
	public String getCartOrderPage(int orderGroup, HttpSession session, Model model) {
		log.info("@@@OrderController의 getCartOrderPage 호출");
		Map<String, Object> map = orderPageService.getCartOrderPage(orderGroup);
		
		//Integer totalPrice = (Integer) session.getAttribute("totalPrice");

//		model.addAttribute("cartOrderPage", cartOrderPage);

		// JSP 파일의 경로를 반환합니다.
		return "board/orderpage";
	}
}

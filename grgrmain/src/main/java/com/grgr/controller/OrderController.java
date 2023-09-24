package com.grgr.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@RequestMapping("/list")
	public String getCartOrderPage(HttpSession session, Model model) {
		int loginUno =(Integer)session.getAttribute("loginUno");
		Map<String, Object> map = orderPageService.getOrderInfo(loginUno);
		model.addAllAttributes(map);
		return "board/orderpage";
	}
}

package com.grgr.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grgr.dto.MyOrderListDTO;
import com.grgr.service.MyOrderListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyOrderListController {
	private final MyOrderListService myOrderListService;

	@RequestMapping("/myOrderList")
	public String orderList(Model model, HttpSession session) {
		Integer uno = (Integer) session.getAttribute("loginUno");
		List<MyOrderListDTO> myOrderList = myOrderListService.getMyOrderList(uno);
		System.out.println(myOrderList);
		model.addAttribute("myOrderList", myOrderList);
		return "mypage/myOrderList";
	}
}

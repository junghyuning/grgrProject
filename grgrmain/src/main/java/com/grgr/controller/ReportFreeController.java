package com.grgr.controller;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grgr.dto.ReportFreeboard;
import com.grgr.service.ReportFreeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/*
작성자 : 김정현
수정일 - 수정내용
0913 - 게시글 신고기능 적용 ( 기능 초안 작성자 : 안소연)
0913 - @pathvariable 삭제 -> bno를 requestBody로 받아 서 사용함(매개변수 최소화)
0913 - @Valid 검증기능 사용 

*/
@RestController
@RequestMapping("/freereport")
@RequiredArgsConstructor
@Slf4j
public class ReportFreeController {
	private final ReportFreeService reportFreeService;
	
	@PostMapping(value="/board-add", produces = "application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<String> addReportFreeBoard(@RequestBody @Valid ReportFreeboard reportFreeboard, Errors  errors, HttpSession session){

		if (errors.hasErrors()) {
			 // 응답헤더 지정

			String errorMessage = errors.getFieldError("reportFreeboardReason").getDefaultMessage();

		    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		}

		Integer loginUno = (Integer) session.getAttribute("loginUno");

		reportFreeboard.setUno(loginUno);

		String result = reportFreeService.addReportFreeBoard(reportFreeboard);

		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}

package com.grgr.controller;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grgr.dto.ReportInfoboard;
import com.grgr.service.ReportInfoService;

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
@RequestMapping("/inforeport")
@RequiredArgsConstructor
@Slf4j
public class ReportInfoController {
	private final ReportInfoService reportInfoService;
	
	@PostMapping("/board-add")
	public ResponseEntity<String> addReportInfoBoard(@RequestBody @Valid ReportInfoboard reportInfoboard, Errors  errors, HttpSession session){
		log.info("addReportInfoBoard");
		if (errors.hasErrors()) {
			log.info("valid - reportInfoboardReason");
			String errorMessage = errors.getFieldError("reportInfoboardReason").getDefaultMessage();
			log.info("errorMessage"+errorMessage);
		    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		}
		log.info("error문 탈출");
		Integer loginUno = (Integer) session.getAttribute("loginUno");
		log.info("loginUno : "+loginUno);
		reportInfoboard.setUno(loginUno);
		log.info("set "+loginUno);
		String result = reportInfoService.addReportInfoBoard(reportInfoboard);
		log.info("result"+result);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}

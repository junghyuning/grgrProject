package com.grgr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grgr.dto.ReportQnaBoard;
import com.grgr.service.ReportQnaBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
@Slf4j
public class ReportQnaBoardController {
	@Autowired
	private ReportQnaBoardService reportQnaBoardService;

	@PostMapping("/board-add/{qnaBno}")
	public ResponseEntity<String> addReportQnaBoard(@PathVariable int qnaBno,
			@RequestBody ReportQnaBoard reportQnaBoard, HttpSession session) {
		log.info("ReportQnaBoardController 클래스의 addReportQnaBoard 메서드");

		// 조회수 증가 처리
		reportQnaBoardService.riseQnaBoardReportCnt(qnaBno);

		Integer loginUno = (Integer) session.getAttribute("loginUno");

		//로그인 사용자가 아닌 경우
		if (loginUno == null) {
			return new ResponseEntity<>("unauthorized_error", HttpStatus.UNAUTHORIZED);
		}

		reportQnaBoard.setUno(loginUno);

		String result = reportQnaBoardService.addReportQnaBoard(qnaBno, reportQnaBoard);

		//신고 중복 확인 
		if ("duplicate".equals(result)) {
			return new ResponseEntity<>("duplicate", HttpStatus.OK);
		}

		return new ResponseEntity<>("success", HttpStatus.OK);
	}
}

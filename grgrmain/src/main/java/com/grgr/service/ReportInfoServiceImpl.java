package com.grgr.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grgr.dao.ReportInfoDAO;
import com.grgr.dao.ReportQnaBoardDAO;
import com.grgr.dto.ReportInfoboard;
import com.grgr.dto.ReportQnaBoard;
import com.grgr.exception.WriteNullException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/*
작성일 : 230913
작성자 : 김정현
작성내용 : 게시물 신고에 대한 메서드 (원본 : 안소연)
수정일 - 수정내용 :
230913 - 별도의 중복검사 메서드 삭제 -> 서비스클래스내부에서 처리
230913 - 별도의 전체 신고수 증가 메서드 삭제 -> 서비스클래스내부에서 미중복시 증가하도록 변경
230913 - @Valid 어노테이션 사용하였으므로 예외처리 삭제
*/
@Service
@RequiredArgsConstructor
@Slf4j
public class ReportInfoServiceImpl implements ReportInfoService {
	private final ReportInfoDAO reportInfoDAO;

	@Transactional
	@Override
	public String addReportInfoBoard(ReportInfoboard reportInfoboard) {

		Map<String, Object> duplicateMap = new HashMap<String, Object>();
		duplicateMap.put("uno", reportInfoboard.getUno());
		duplicateMap.put("infoBno", reportInfoboard.getInfoBno());
		

		int duplicateResult = reportInfoDAO.checkDuplicateReport(duplicateMap);
		if (duplicateResult > 0) {
			return "duplicate";
		}

		reportInfoDAO.insertReportInfoBoard(reportInfoboard);
		reportInfoDAO.increaseInfoBoardReportCnt(reportInfoboard.getInfoBno());

		return "success";
	}

}

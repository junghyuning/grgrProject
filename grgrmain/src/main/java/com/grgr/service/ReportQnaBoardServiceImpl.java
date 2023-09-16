package com.grgr.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grgr.dao.ReportQnaBoardDAO;
import com.grgr.dto.ReportQnaBoard;
import com.grgr.exception.WriteNullException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportQnaBoardServiceImpl implements ReportQnaBoardService {
	private final ReportQnaBoardDAO reportQnaBoardDAO;

	@Transactional
	@Override
	public String addReportQnaBoard(int qnaBno, ReportQnaBoard reportQnaBoard)
			throws WriteNullException {
		log.info("ReportQnaBoardServiceImpl 클래스의 addReportQnaBoard 메서드");

		Map<String, Object> duplicateMap = new HashMap<String, Object>();
		duplicateMap.put("uno", reportQnaBoard.getUno());
		duplicateMap.put("qnaBno", qnaBno);
		reportQnaBoardDAO.checkDuplicateReport(duplicateMap);

		int duplicateResult = duplicateReport(duplicateMap);
		if (duplicateResult == 1) {
			return "duplicate";
		}

		Map<String, Object> addMap = new HashMap<String, Object>();
		addMap.put("reportQnaBoardNo", reportQnaBoard.getReportQnaBoardNo());
		addMap.put("uno", reportQnaBoard.getUno());
		addMap.put("qnaBno", qnaBno);
		addMap.put("reportReason", reportQnaBoard.getReportReason());

		reportQnaBoardDAO.insertReportQnaBoard(addMap);

		return "success";
	}

	
	@Override
	public int riseQnaBoardReportCnt(int qnaBno) {
		return reportQnaBoardDAO.increaseQnaBoardReportCnt(qnaBno);
	}

	@Override
	public ReportQnaBoard getReportQnaBoard(int reportQnaBoardNo) {
		ReportQnaBoard reportQnaBoard = reportQnaBoardDAO.selectReportQnaBoard(reportQnaBoardNo);

		if (reportQnaBoard != null) {
			String reportConentIncludeEnter = reportQnaBoard.getReportReason().replace("\r\n", "<br>");
			reportQnaBoard.setReportReason(reportConentIncludeEnter);
		}

		return reportQnaBoard;
	}

	@Override
	public int duplicateReport(Map<String, Object> map) {
		int result = reportQnaBoardDAO.checkDuplicateReport(map);

		if (result > 0) {
			return 1;// 중복
		} else {
			return 0;// 미중복
		}
	}

}

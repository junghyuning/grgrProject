package com.grgr.service;

import java.util.Map;

import com.grgr.dto.ReportQnaBoard;
import com.grgr.exception.WriteNullException;

public interface ReportQnaBoardService {
	String addReportQnaBoard(int qnaBno, ReportQnaBoard reportQnaBoard) throws WriteNullException;//신고게시글 삽입
	int riseQnaBoardReportCnt(int qnaBno);//신고게시글 카운트
	ReportQnaBoard getReportQnaBoard(int reportQnaBoardNo);//신고게시글 조회
	int duplicateReport(Map<String, Object> map);//신고 중복 여부
}

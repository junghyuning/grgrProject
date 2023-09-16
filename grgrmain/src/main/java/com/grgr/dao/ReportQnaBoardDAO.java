package com.grgr.dao;

import java.util.Map;

import com.grgr.dto.ReportQnaBoard;

public interface ReportQnaBoardDAO {
	int insertReportQnaBoard(Map<String, Object> map);//신고게시글 삽입
	int increaseQnaBoardReportCnt(int qnaBno);//신고게시글 카운트
	ReportQnaBoard selectReportQnaBoard(int reportQnaBoardNo);//신고게시글 조회
	int checkDuplicateReport(Map<String, Object> map);//신고 중복 여부
}

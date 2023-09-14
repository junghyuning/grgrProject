package com.grgr.mapper;

import java.util.Map;

import com.grgr.dto.ReportInfoboard;
/*
작성자 : 김정현
수정일 - 수정내용 
230913 - insertReportInfoBoard의 매개변수를 MAP객체 ->  ReportInfoboard 객체로 변경
 */

public interface ReportInfoMapper {
	int insertReportInfoBoard(ReportInfoboard reportInfoboard);//신고 게시글 삽입
	int increaseInfoBoardReportCnt(int qnaBno); // 신고 게시글 카운트
	int checkDuplicateReport(Map<String, Object> map); // 게시글 신고 중복여부 체크
}

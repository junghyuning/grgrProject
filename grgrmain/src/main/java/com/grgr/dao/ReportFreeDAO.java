package com.grgr.dao;

import java.util.Map;

import com.grgr.dto.ReportFreeboard;

public interface ReportFreeDAO {
	int insertReportFreeBoard(ReportFreeboard reportFreeboard);//신고 게시글 삽입
	int increaseFreeBoardReportCnt(int freeBno); // 신고 게시글 카운트
	int checkDuplicateReport(Map<String, Object> map); // 게시글 신고 중복여부 체크
}

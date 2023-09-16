package com.grgr.dao;

import java.util.Map;

import com.grgr.dto.ReportInfoboard;
import com.grgr.dto.ReportQnaBoard;

public interface ReportInfoDAO {
	int insertReportInfoBoard(ReportInfoboard reportInfoboard);//신고 게시글 삽입
	int increaseInfoBoardReportCnt(int infoBno); // 신고 게시글 카운트
	int checkDuplicateReport(Map<String, Object> map); // 게시글 신고 중복여부 체크
}

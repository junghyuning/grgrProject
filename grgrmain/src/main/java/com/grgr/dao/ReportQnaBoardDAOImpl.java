package com.grgr.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.ReportQnaBoard;
import com.grgr.mapper.ReportQnaBoardMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReportQnaBoardDAOImpl implements ReportQnaBoardDAO {
	private final SqlSession sqlSession;
	
	@Override
	public int insertReportQnaBoard(Map<String, Object> map) {
		return sqlSession.getMapper(ReportQnaBoardMapper.class).insertReportQnaBoard(map);
	}
	
	@Override
	public int increaseQnaBoardReportCnt(int qnaBno) {
		return sqlSession.getMapper(ReportQnaBoardMapper.class).increaseQnaBoardReportCnt(qnaBno);
	}

	@Override
	public ReportQnaBoard selectReportQnaBoard(int reportQnaBoardNo) {
		return sqlSession.getMapper(ReportQnaBoardMapper.class).selectReportQnaBoard(reportQnaBoardNo);
	}

	@Override
	public int checkDuplicateReport(Map<String, Object> map) {
		return sqlSession.getMapper(ReportQnaBoardMapper.class).checkDuplicateReport(map);
	}
}

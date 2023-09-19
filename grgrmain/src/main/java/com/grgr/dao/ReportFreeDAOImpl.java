package com.grgr.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.ReportFreeboard;
import com.grgr.mapper.ReportFreeMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReportFreeDAOImpl implements ReportFreeDAO {
	private final SqlSession sqlSession;

	@Override
	public int insertReportFreeBoard(ReportFreeboard reportFreeboard) {
		return sqlSession.getMapper(ReportFreeMapper.class).insertReportFreeBoard(reportFreeboard);
	}

	@Override
	public int increaseFreeBoardReportCnt(int freeBno) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ReportFreeMapper.class).increaseFreeBoardReportCnt(freeBno);
	}

	@Override
	public int checkDuplicateReport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ReportFreeMapper.class).checkDuplicateReport(map);
	}

}

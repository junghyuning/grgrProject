package com.grgr.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.ReportInfoboard;
import com.grgr.dto.ReportQnaBoard;
import com.grgr.mapper.ReportInfoMapper;
import com.grgr.mapper.ReportQnaBoardMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReportInfoDAOImpl implements ReportInfoDAO {
	private final SqlSession sqlSession;

	@Override
	public int insertReportInfoBoard(ReportInfoboard reportInfoboard) {
		return sqlSession.getMapper(ReportInfoMapper.class).insertReportInfoBoard(reportInfoboard);
	}

	@Override
	public int increaseInfoBoardReportCnt(int infoBno) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ReportInfoMapper.class).increaseInfoBoardReportCnt(infoBno);
	}

	@Override
	public int checkDuplicateReport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ReportInfoMapper.class).checkDuplicateReport(map);
	}

}

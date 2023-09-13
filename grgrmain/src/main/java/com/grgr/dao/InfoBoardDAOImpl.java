package com.grgr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.InfoBoard;
import com.grgr.dto.InfoFile;
import com.grgr.mapper.InfoBoardMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InfoBoardDAOImpl implements InfoBoardDAO{
	private final SqlSession sqlSession;

	@Override
	public int infoBoardCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(InfoBoardMapper.class).infoBoardCount(map);
	}

	@Override
	public int insertInfoBoard(InfoBoard infoBoard) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(InfoBoardMapper.class).insertInfoBoard(infoBoard);
	}

	@Override
	public int updateInfoBoard(InfoBoard infoBoard) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(InfoBoardMapper.class).updateInfoBoard(infoBoard);
	}

	@Override
	public int deleteInfoBoard(int infoBno, int uno) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(InfoBoardMapper.class).deleteInfoBoard(infoBno, uno);
	}

	@Override
	public int blindInfoBoard(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(InfoBoardMapper.class).blindInfoBoard(map);
	}

	@Override
	public int increaseInfoViewCnt(int infoBno) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(InfoBoardMapper.class).increaseInfoViewCnt(infoBno);
	}

	@Override
	public int increaseInfoReportNo(int infoBno) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(InfoBoardMapper.class).increaseInfoReportNo(infoBno);
	}

	@Override
	public InfoBoard selectInfoBoard(int infoBno) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(InfoBoardMapper.class).selectInfoBoard(infoBno);
	}

	@Override
	public List<InfoBoard> selectInfoBoardList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(InfoBoardMapper.class).selectInfoBoardList(map);
	}

	@Override
	public Integer selectPrevInfoBno(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(InfoBoardMapper.class).selectPrevInfoBno(map);
	}

	@Override
	public Integer selectNextInfoBno(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(InfoBoardMapper.class).selectNextInfoBno(map);
	}

	@Override
	public int insertInfoFile(InfoFile infoFile) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(InfoBoardMapper.class).insertInfoFile(infoFile);
	}

	@Override
	public List<InfoFile> selectInfoFile(int infoBno) {
		return sqlSession.getMapper(InfoBoardMapper.class).selectInfoFile(infoBno);
	}

	@Override
	public int deleteInfoFile(int infoFileNo) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(InfoBoardMapper.class).deleteInfoFile(infoFileNo);
	}
	
}

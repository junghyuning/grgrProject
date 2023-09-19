package com.grgr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.FreeBoard;
import com.grgr.dto.FreeFile;
import com.grgr.mapper.FreeBoardMapper;

import lombok.RequiredArgsConstructor;
@Repository
@RequiredArgsConstructor
public class FreeBoardDAOImpl implements FreeBoardDAO {
	private final SqlSession sqlSession;

	
	@Override
	public int freeBoardCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(FreeBoardMapper.class).freeBoardCount(map);
	}

	@Override
	public int insertFreeBoard(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(FreeBoardMapper.class).insertFreeBoard(freeBoard);
	}

	@Override
	public int updateFreeBoard(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(FreeBoardMapper.class).updateFreeBoard(freeBoard);
	}

	@Override
	public int deleteFreeBoard(int freeBno, int uno) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(FreeBoardMapper.class).deleteFreeBoard(freeBno, uno);
	}

	@Override
	public int blindFreeBoard(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(FreeBoardMapper.class).blindFreeBoard(map);
	}

	@Override
	public int increaseFreeViewCnt(int freeBno) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(FreeBoardMapper.class).increaseFreeViewCnt(freeBno);
	}



	@Override
	public FreeBoard selectFreeBoard(int freeBno) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(FreeBoardMapper.class).selectFreeBoard(freeBno);
	}

	@Override
	public Integer selectPrevFreeBno(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(FreeBoardMapper.class).selectPrevFreeBno(map);
	}

	@Override
	public Integer selectNextFreeBno(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(FreeBoardMapper.class).selectNextFreeBno(map);
	}

	@Override
	public List<FreeBoard> selectFreeBoardList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(FreeBoardMapper.class).selectFreeBoardList(map);
	}

	@Override
	public int insertFreeFile(FreeFile freeFile) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(FreeBoardMapper.class).insertFreeFile(freeFile);
	}

	@Override
	public List<FreeFile> selectFreeFile(int freeBno) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(FreeBoardMapper.class).selectFreeFile(freeBno);
	}

	@Override
	public int deleteFreeFile(int freeFileNo) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(FreeBoardMapper.class).deleteFreeFile(freeFileNo);
	}

}

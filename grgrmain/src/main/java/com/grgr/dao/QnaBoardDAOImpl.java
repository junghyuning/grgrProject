package com.grgr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.QnaBoard;
import com.grgr.mapper.QnaBoardMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QnaBoardDAOImpl implements QnaBoardDAO {
	private final SqlSession sqlSession;

	@Override
	public int qnaBoardCount(Map<String, Object> map) {
		return sqlSession.getMapper(QnaBoardMapper.class).qnaBoardCount(map);
	}

	@Override
	public int insertQnaBoard(QnaBoard qnaBoard) {
		return sqlSession.getMapper(QnaBoardMapper.class).insertQnaBoard(qnaBoard);
	}

	@Override
	public int updateQnaBoard(QnaBoard qnaBoard) {
		return sqlSession.getMapper(QnaBoardMapper.class).updateQnaBoard(qnaBoard);
	}

	@Override
	public int deleteQnaBoard(int qnaBno, int uno) {
		return sqlSession.getMapper(QnaBoardMapper.class).deleteQnaBoard(qnaBno, uno);
	}

	@Override
	public int blindQnaBoard(Map<String, Object> map) {
		return sqlSession.getMapper(QnaBoardMapper.class).blindQnaBoard(map);
	}
	
	@Override
	public int increaseQnaViewCnt(int qnaBno) {
		return sqlSession.getMapper(QnaBoardMapper.class).increaseQnaViewCnt(qnaBno);
	}
	
	@Override
	public QnaBoard selectQnaBoard(int qnaBno) {
		return sqlSession.getMapper(QnaBoardMapper.class).selectQnaBoard(qnaBno);
	}
	
	@Override
	public Integer selectPrevQnaBno(Map<String, Object> map) {
		return sqlSession.getMapper(QnaBoardMapper.class).selectPrevQnaBno(map);
	}

	@Override
	public Integer selectNextQnaBno(Map<String, Object> map) {
		return sqlSession.getMapper(QnaBoardMapper.class).selectNextQnaBno(map);
	}

	@Override
	public List<QnaBoard> selectQnaBoardList(Map<String, Object> map) {
		return sqlSession.getMapper(QnaBoardMapper.class).selectQnaBoardList(map);
	}

	
}

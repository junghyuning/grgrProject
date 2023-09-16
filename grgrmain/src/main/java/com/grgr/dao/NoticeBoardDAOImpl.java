package com.grgr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.grgr.dto.NoticeBoard;
import com.grgr.mapper.NoticeBoardMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class NoticeBoardDAOImpl implements NoticeBoardDAO {
	private final SqlSession sqlSession;

	@Override
	public int noticeBoardCount(Map<String, Object> map) {
		return sqlSession.getMapper(NoticeBoardMapper.class).noticeBoardCount(map);
	}

	@Override
	public int insertNoticeBoard(NoticeBoard noticeBoard) {
		return sqlSession.getMapper(NoticeBoardMapper.class).insertNoticeBoard(noticeBoard);
	}

	@Override
	public int updateNoticeBoard(NoticeBoard noticeBoard) {
		return sqlSession.getMapper(NoticeBoardMapper.class).updateNoticeBoard(noticeBoard);
	}

	@Override
	public int deleteNoticeBoard(int noticeBno, int uno, int loginUserStatus) {
		return sqlSession.getMapper(NoticeBoardMapper.class).deleteNoticeBoard(noticeBno, uno, loginUserStatus);
	}

	@Override
	public int increaseNoticeViewCnt(int noticeBno) {
		return sqlSession.getMapper(NoticeBoardMapper.class).increaseNoticeViewCnt(noticeBno);
	}

	@Override
	public NoticeBoard selectNoticeBoard(int noticeBno) {
		return sqlSession.getMapper(NoticeBoardMapper.class).selectNoticeBoard(noticeBno);
	}

	@Override
	public Integer selectPrevNoticeBno(Map<String, Object> map) {
		return sqlSession.getMapper(NoticeBoardMapper.class).selectPrevNoticeBno(map);
	}

	@Override
	public Integer selectNextNoticeBno(Map<String, Object> map) {
		return sqlSession.getMapper(NoticeBoardMapper.class).selectNextNoticeBno(map);
	}

	@Override
	public List<NoticeBoard> selectNoticeBoardList(Map<String, Object> map) {
		return sqlSession.getMapper(NoticeBoardMapper.class).selectNoticeBoardList(map);
	}
	
}

package com.grgr.dao;

import java.util.List;
import java.util.Map;

import com.grgr.dto.NoticeBoard;

public interface NoticeBoardDAO {
	int noticeBoardCount(Map<String, Object> map);
	int insertNoticeBoard(NoticeBoard noticeBoard);
	int updateNoticeBoard(NoticeBoard noticeBoard);
	int deleteNoticeBoard(int noticeBno, int uno, int loginUserStatus);
	int increaseNoticeViewCnt(int noticeBno);
	NoticeBoard selectNoticeBoard(int noticeBno);
	Integer selectPrevNoticeBno(Map<String, Object> map);
	Integer selectNextNoticeBno(Map<String, Object> map);
	List<NoticeBoard> selectNoticeBoardList(Map<String, Object> map);

}

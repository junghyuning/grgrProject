package com.grgr.service;

import java.io.IOException;
import java.util.Map;

import com.grgr.dto.NoticeBoard;
import com.grgr.exception.WriteNullException;
import com.grgr.util.SearchCondition;

public interface NoticeBoardService {
	int getNoticeCount(SearchCondition searchCondition) ;
	int addNoticeBoard(NoticeBoard noticeBoard)throws WriteNullException, IOException;
	void modifyNoticeBoard(NoticeBoard noticeBoard);
	void removeNoticeBoard(int noticeBno, int uno, int loginUserStatus);
	int increaseNoticeViewCnt(int noticeBno);
	Map<String, Object> getNoticeBoard(int noticeBno);
	Integer prevNoticeBno(SearchCondition searchCondition, int noticeBno);
	Integer nextNoticeBno(SearchCondition searchCondition, int noticeBno);
	Map<String, Object> getNoticeBoardList(SearchCondition searchCondition);
	
}

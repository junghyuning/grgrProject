package com.grgr.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.grgr.dto.NoticeBoard;

public interface NoticeBoardMapper {
	int noticeBoardCount(Map<String, Object> map);
	int insertNoticeBoard(NoticeBoard noticeBoard);
	int updateNoticeBoard(NoticeBoard noticeBoard);
	int deleteNoticeBoard(@Param("noticeBno") int noticeBno, @Param("uno") int uno, @Param("userStatus") int loginUserStatus);
	int increaseNoticeViewCnt(int noticeBno);
	NoticeBoard selectNoticeBoard(int noticeBno);
	Integer selectPrevNoticeBno(Map<String, Object> map);
	Integer selectNextNoticeBno(Map<String, Object> map);
	List<NoticeBoard> selectNoticeBoardList(Map<String, Object> map);
	
}

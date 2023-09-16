package com.grgr.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grgr.dao.NoticeBoardDAO;
import com.grgr.dto.NoticeBoard;
import com.grgr.exception.WriteNullException;
import com.grgr.util.Pager;
import com.grgr.util.SearchCondition;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeBoardServiceImpl implements NoticeBoardService {
	private final NoticeBoardDAO noticeBoardDAO;

	@Override
	public int getNoticeCount(SearchCondition searchCondition) {
		Map<String, Object> searchMap = createSearchMap(searchCondition);
		
		return noticeBoardDAO.noticeBoardCount(searchMap);
	}
	
	@Override
	@Transactional
	public int addNoticeBoard(NoticeBoard noticeBoard) throws WriteNullException, IOException {
		
		if (noticeBoard.getNoticeTitle() == null || noticeBoard.getNoticeContent() == null) {
	        throw new WriteNullException("제목 또는 내용이 비어있습니다.");
	    }
		noticeBoardDAO.insertNoticeBoard(noticeBoard);

		return noticeBoard.getNoticeBno();
	}

	@Override
	public void modifyNoticeBoard(NoticeBoard noticeBoard) {
		noticeBoardDAO.updateNoticeBoard(noticeBoard);
	}

	@Override
	public void removeNoticeBoard(int noticeBno, int uno, int loginUserStatus) {
		noticeBoardDAO.deleteNoticeBoard(noticeBno, uno, loginUserStatus);
	}

	@Override
	public Map<String, Object> getNoticeBoard(int noticeBno) {
		Map<String, Object> readMap = new HashMap<String, Object>();
		NoticeBoard noticeBoard = noticeBoardDAO.selectNoticeBoard(noticeBno);
		String noticeConentIncludeEnter = noticeBoard.getNoticeContent().replace("\r\n", "<br>"); //개행문자를 포함하여 출력하기위함
		noticeBoard.setNoticeContent(noticeConentIncludeEnter);
		readMap.put("noticeBoard", noticeBoard);
		
		return readMap;
	}

	@Override
	public Map<String, Object> getNoticeBoardList(SearchCondition searchCondition)  {
		Map<String, Object> searchMap = createSearchMap(searchCondition);

		int totalBoard = getNoticeCount(searchCondition);
		
		Pager pager = new Pager(totalBoard, searchCondition);
		// 페이징 계산
		searchMap.put("startRow", pager.getStartRow());
		searchMap.put("endRow", pager.getEndRow());

		List<NoticeBoard> noticeBoardList = noticeBoardDAO.selectNoticeBoardList(searchMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("noticeBoardList", noticeBoardList);
		resultMap.put("pager", pager); // pager 객체를 반환

		return resultMap;
	}

	@Override
	public Integer prevNoticeBno(SearchCondition searchCondition, int noticeBno) {
		Map<String, Object> searchMap = createSearchMap(searchCondition);
		searchMap.put("noticeBno", noticeBno);
		
		return noticeBoardDAO.selectPrevNoticeBno(searchMap);
	}

	@Override
	public Integer nextNoticeBno(SearchCondition searchCondition, int noticeBno) {
		Map<String, Object> searchMap = createSearchMap(searchCondition);
		searchMap.put("noticeBno", noticeBno);		

		return noticeBoardDAO.selectNextNoticeBno(searchMap);
	}

	@Override
	public int increaseNoticeViewCnt(int noticeBno) {
		return noticeBoardDAO.increaseNoticeViewCnt(noticeBno);
	}
		
	//검색관련 맵객체 생성 메서드
	private Map<String, Object> createSearchMap (SearchCondition searchCondition){
		Map<String, Object> searchMap = new HashMap<String, Object>();
		if (searchCondition != null) {
			if (searchCondition.getSearchType() != null && !searchCondition.getSearchType().isEmpty()) {
				searchMap.put("searchType", searchCondition.getSearchType());
			}
			if (searchCondition.getSearchKeyword() != null && !searchCondition.getSearchKeyword().isEmpty()) {
				searchMap.put("searchKeyword", searchCondition.getSearchKeyword());
			}
//				if (searchCondition.getKeyword() != null && !searchCondition.getKeyword().trim().isEmpty()) {
//					searchMap.put("infoKeyword", searchCondition.getKeyword().trim());
//				}
		}
		return searchMap;
	}
}

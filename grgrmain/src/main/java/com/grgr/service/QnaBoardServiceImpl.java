package com.grgr.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.grgr.dao.QnaBoardDAO;
import com.grgr.dto.QnaBoard;
import com.grgr.util.Pager;
import com.grgr.util.SearchCondition;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaBoardServiceImpl implements QnaBoardService {
	private final QnaBoardDAO qnaBoardDAO;

	@Override
	public int getQnaCount(SearchCondition searchCondition) {
		Map<String, Object> searchMap = createSearchMap(searchCondition);
		
		return qnaBoardDAO.qnaBoardCount(searchMap);
	}

	@Override
	public int addQnaBoard(QnaBoard qnaBoard) {
		if (qnaBoard.getQnaTitle() == null || qnaBoard.getQnaContent() == null) {
	        
	    }
		
		qnaBoardDAO.insertQnaBoard(qnaBoard);

		return qnaBoard.getQnaBno();
	}

	@Override
	public void modifyQnaBoard(QnaBoard qnaBoard) {
		qnaBoardDAO.updateQnaBoard(qnaBoard);
	}

	@Override
	public void removeQnaBoard(int qnaBno, int uno) {
		qnaBoardDAO.deleteQnaBoard(qnaBno, uno);	
	}

	@Override
	public void hideQnaBoard(int qnaBno, int loginUno, int loginUserStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qnaBno", qnaBno);
		map.put("uno", loginUno);
		map.put("userStatus", loginUserStatus);
		
		qnaBoardDAO.blindQnaBoard(map);
	}
	
	@Override
	public int riseQnaViewCnt(int qnaBno) {
		return qnaBoardDAO.increaseQnaViewCnt(qnaBno);
	}
	
	@Override
	public Map<String, Object> getQnaBoard(int qnaBno) {
		Map<String, Object> readMap = new HashMap<String, Object>();

		QnaBoard qnaBoard = qnaBoardDAO.selectQnaBoard(qnaBno);
		String qnaConentIncludeEnter = qnaBoard.getQnaContent().replace("\r\n", "<br>"); //개행문자를 포함하여 출력하기위함
		qnaBoard.setQnaContent(qnaConentIncludeEnter);
		readMap.put("qnaBoard", qnaBoard);
		
		return readMap;
	}		

	@Override
	public Map<String, Object> getQnaBoardList(SearchCondition searchCondition) {
		Map<String, Object> searchMap = createSearchMap(searchCondition);
		int totalBoard = getQnaCount(searchCondition);
		
		Pager pager = new Pager(totalBoard, searchCondition);
		// 페이징 계산
		searchMap.put("startRow", pager.getStartRow());
		searchMap.put("endRow", pager.getEndRow());

		List<QnaBoard> qnaBoardList = qnaBoardDAO.selectQnaBoardList(searchMap);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("qnaBoardList", qnaBoardList);
		resultMap.put("pager", pager); // pager 객체를 반환
		//resultMap.put("searchMap", searchMap);

		return resultMap;
	}
	
	@Override
	public Integer prevQnaBno(SearchCondition searchCondition, int qnaBno) {
		Map<String, Object> searchMap = createSearchMap(searchCondition);
		searchMap.put("qnaBno", qnaBno);
		
		return qnaBoardDAO.selectPrevQnaBno(searchMap);
	}

	@Override
	public Integer nextQnaBno(SearchCondition searchCondition, int qnaBno) {
		Map<String, Object> searchMap = createSearchMap(searchCondition);
		searchMap.put("qnaBno", qnaBno);

		return qnaBoardDAO.selectNextQnaBno(searchMap);
	}
	
	//검색관련 맵객체 생성 메서드
	private Map<String, Object> createSearchMap(SearchCondition searchCondition){
		Map<String, Object> searchMap = new HashMap<String, Object>();
		if (searchCondition != null) {
			if (searchCondition.getSearchType() != null && !searchCondition.getSearchType().isEmpty()) {
				searchMap.put("searchType", searchCondition.getSearchType());
			}
			if (searchCondition.getSearchKeyword() != null && !searchCondition.getSearchKeyword().isEmpty()) {
				searchMap.put("searchKeyword", searchCondition.getSearchKeyword());
			}
			if (searchCondition.getKeyword() != null && !searchCondition.getKeyword().trim().isEmpty()) {
				searchMap.put("qnaKeyword", searchCondition.getKeyword().trim());
			}
//				if (searchCondition.getLocation() != null && !searchCondition.getLocation().trim().isEmpty()) {
//					searchMap.put("infoLoc", searchCondition.getLocation().trim());
//				}
		}
		return searchMap;
	}
}
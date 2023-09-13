package com.grgr.service;

import java.util.Map;

import com.grgr.dto.QnaBoard;
import com.grgr.util.SearchCondition;

public interface QnaBoardService {
	int getQnaCount(SearchCondition searchCondition);
	int addQnaBoard(QnaBoard qnaBoard);
	void modifyQnaBoard(QnaBoard qnaBoard);
	void removeQnaBoard(int qnaBno, int uno);
	void hideQnaBoard(int qnaBno, int loginUser, int loginUserStatus);
	int riseQnaViewCnt(int qnaBno);//조회수 증가
	Map<String, Object> getQnaBoard(int qnaBno);
	Integer prevQnaBno(SearchCondition searchCondition, int qnaBno);
	Integer nextQnaBno(SearchCondition searchCondition, int qnaBno);
	Map<String, Object> getQnaBoardList(SearchCondition searchCondition);
}

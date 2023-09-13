package com.grgr.dao;

import java.util.List;
import java.util.Map;

import com.grgr.dto.QnaBoard;

public interface QnaBoardDAO {
	int qnaBoardCount(Map<String, Object> map);
	int insertQnaBoard(QnaBoard qnaBoard);
	int updateQnaBoard(QnaBoard qnaBoard);
	int deleteQnaBoard(int qnaBno, int uno);
	int blindQnaBoard(Map<String, Object> map);
	int increaseQnaViewCnt(int qnaBno);
	QnaBoard selectQnaBoard(int qnaBno);
	Integer selectPrevQnaBno(Map<String, Object> map);
	Integer selectNextQnaBno(Map<String, Object> map);
	List<QnaBoard> selectQnaBoardList(Map<String, Object> map);
	
}

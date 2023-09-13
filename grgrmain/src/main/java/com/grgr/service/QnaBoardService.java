package com.grgr.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.grgr.dto.QnaBoard;
import com.grgr.exception.FileUploadFailException;
import com.grgr.exception.WriteNullException;
import com.grgr.util.SearchCondition;

public interface QnaBoardService {
	int getQnaCount(SearchCondition searchCondition);
	int addQnaBoard(QnaBoard qnaBoard, List<MultipartFile> files)
			throws WriteNullException, FileUploadFailException, IOException;
	void modifyQnaBoard(QnaBoard qnaBoard);
	void removeQnaBoard(int qnaBno, int uno);
	void hideQnaBoard(int qnaBno, int loginUser, int loginUserStatus);
	int riseQnaViewCnt(int qnaBno);// 조회수 증가
	Map<String, Object> getQnaBoard(int qnaBno);
	Integer prevQnaBno(SearchCondition searchCondition, int qnaBno);
	Integer nextQnaBno(SearchCondition searchCondition, int qnaBno);
	Map<String, Object> getQnaBoardList(SearchCondition searchCondition);
}

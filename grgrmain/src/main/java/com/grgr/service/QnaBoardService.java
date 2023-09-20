package com.grgr.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.grgr.dto.QnaBoard;
import com.grgr.exception.FileDeleteException;
import com.grgr.exception.FileUploadFailException;
import com.grgr.exception.PostUpdateException;
import com.grgr.exception.WriteNullException;
import com.grgr.util.SearchCondition;

//0914 - 안소연_사진 업로드 추가
//		getQnaBoardList : 세션 추가, addQnaBoard : isSecret - 비밀글 여부 추가, modifyQnaBoard : isSecret - 비밀글 여부 추가
//0920 - 안소연_관리자가 비밀글 조회 가능하도록 수정(loginUserStatus 추가)

public interface QnaBoardService {
	int addQnaBoard(QnaBoard qnaBoard, Integer qnaBlindstate, List<MultipartFile> files) throws WriteNullException, FileUploadFailException, IOException;
	void modifyQnaBoard(QnaBoard qnaBoard, Integer qnaBlindstate, List<MultipartFile> files) throws WriteNullException, FileUploadFailException, IOException;
	void removeQnaBoard(int qnaBno, int uno) throws PostUpdateException;
	void hideQnaBoard(int qnaBno, int loginUser, int loginUserStatus);
	Map<String, Object> getQnaBoard(int loginUno, int qnaBno);
	Integer prevQnaBno(SearchCondition searchCondition, int qnaBno);
	Integer nextQnaBno(SearchCondition searchCondition, int qnaBno);
	void removeQnaFiles(List<Integer> deleteFileList) throws FileDeleteException;
	Map<String, Object> getQnaBoardList(SearchCondition searchCondition, HttpSession session, int loginUserStatus);
}

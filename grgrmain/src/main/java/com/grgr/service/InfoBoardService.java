package com.grgr.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.grgr.dto.InfoBoard;
import com.grgr.exception.FileUploadFailException;
import com.grgr.exception.WriteNullException;
import com.grgr.util.SearchCondition;

public interface InfoBoardService {
	int getInfoCount(SearchCondition searchCondition);
	int addInfoBoard(InfoBoard infoBoard, List<MultipartFile> files)throws WriteNullException, FileUploadFailException, IOException;
	void modifyInfoBoard(InfoBoard infoBoard);
	void removeInfoBoard(int infoBno, int uno);
	void hideInfoBoard(int infoBno, int loginUser, int loginUserStatus);
//	int increaseInfoViewCnt(int infoBno);
//	int increaseInfoReportNo(int infoBno);
	Map<String, Object> getInfoBoard(int infoBno);
	Integer prevInfoBno(SearchCondition searchCondition, int infoBno);
	Integer nextInfoBno(SearchCondition searchCondition, int infoBno);
	Map<String, Object> getInfoBoardList(SearchCondition searchCondition);
	
}

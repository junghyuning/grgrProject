package com.grgr.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
 
import org.springframework.web.multipart.MultipartFile;

import com.grgr.dto.FreeBoard;
import com.grgr.exception.FileDeleteException;
import com.grgr.exception.FileUploadFailException;
import com.grgr.exception.PostUpdateException;
import com.grgr.exception.WriteNullException;
import com.grgr.util.SearchCondition;

public interface FreeBoardService {

	int addFreeBoard(FreeBoard freeBoard, List<MultipartFile> files)throws WriteNullException, FileUploadFailException, IOException;
	void modifyFreeBoard(FreeBoard freeBoard, List<MultipartFile> files) throws WriteNullException, FileUploadFailException, IOException;
	void removeFreeBoard(int freeBno, int uno) throws PostUpdateException;
	void hideFreeBoard(int freeBno, int loginUser, int loginUserStatus);
	Map<String, Object> getFreeBoard(int loginUno, int freeBno);
	Map<String, Object> prevAndNextFreeBno(SearchCondition searchCondition, int freeBno);
	Map<String, Object> getFreeBoardList(SearchCondition searchCondition);
	void removeFreeFiles(List<Integer> deleteFileList) throws FileDeleteException;
}

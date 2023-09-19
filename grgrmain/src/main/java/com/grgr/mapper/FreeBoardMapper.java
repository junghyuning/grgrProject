package com.grgr.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.grgr.dto.FreeBoard;
import com.grgr.dto.FreeFile;

public interface FreeBoardMapper {
	int freeBoardCount(Map<String, Object> map);
	int insertFreeBoard(FreeBoard freeBoard);
	int updateFreeBoard(FreeBoard freeBoard);
	int deleteFreeBoard(@Param("freeBno") int freeBno,@Param("uno") int uno);
	int blindFreeBoard(Map<String, Object> map);
	int increaseFreeViewCnt(int freeBno);
	FreeBoard selectFreeBoard(int freeBno);
	Integer selectPrevFreeBno(Map<String, Object> map);
	Integer selectNextFreeBno(Map<String, Object> map);
	List<FreeBoard> selectFreeBoardList(Map<String, Object> map);

	int insertFreeFile(FreeFile freeFile);
	List<FreeFile> selectFreeFile(int freeBno);
	int deleteFreeFile(int infoFileNo);
}
